package com.alexgilleran.icesoap.request.impl;

import java.util.List;

import android.os.AsyncTask;

import com.alexgilleran.icesoap.envelope.SOAPEnv;
import com.alexgilleran.icesoap.observer.ListObserverRegistry;
import com.alexgilleran.icesoap.observer.SOAPListObserver;
import com.alexgilleran.icesoap.parser.ListParser;
import com.alexgilleran.icesoap.parser.ParserObserver;
import com.alexgilleran.icesoap.request.ListRequest;

public class ListRequestImpl<T> extends RequestImpl<List<T>> implements
		ListRequest<T> {
	private ListParser<T> parser;
	private ListObserverRegistry<T> itemRegistry = new ListObserverRegistry<T>(
			this);

	public ListRequestImpl(String url, ListParser<T> parser, SOAPEnv soapEnv) {
		super(url, parser, soapEnv);

		this.parser = parser;
	}

	@Override
	public void registerObserver(SOAPListObserver<T> observer) {
		super.addObserver(observer);

		itemRegistry.addObserver(observer);
	}

	@Override
	public void deregisterObserver(SOAPListObserver<T> observer) {
		super.removeObserver(observer);

		itemRegistry.removeObserver(observer);
	}

	@Override
	protected AsyncTask<Void, T, List<T>> createTask() {
		return new ListRequestTask();
	}

	private class ListRequestTask extends RequestTask<T> {
		@Override
		protected void onPreExecute() {
			parser.addItemObserver(itemObserver);
		}

		@Override
		protected void onProgressUpdate(T... item) {
			itemRegistry.notifyNewItem(ListRequestImpl.this, item[0]);
		}

		private ParserObserver<T> itemObserver = new ParserObserver<T>() {
			@SuppressWarnings("unchecked")
			@Override
			public void onNewItem(T item) {
				publishProgress(item);
			}
		};
	}
}