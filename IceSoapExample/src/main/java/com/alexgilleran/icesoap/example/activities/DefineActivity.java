package com.alexgilleran.icesoap.example.activities;

import roboguice.activity.RoboActivity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alexgilleran.icesoap.example.R;
import com.alexgilleran.icesoap.example.dao.DictionaryRequestFactory;
import com.alexgilleran.icesoap.example.domain.Definition;
import com.alexgilleran.icesoap.exception.SOAPException;
import com.alexgilleran.icesoap.observer.SOAPObserver;
import com.alexgilleran.icesoap.request.Request;
import com.google.inject.Inject;

public class DefineActivity extends RoboActivity {
	public static final String DICT_ID_KEY = "dictidkey";
	public static final String DICT_NAME_KEY = "dictnamekey";

	@Inject
	private DictionaryRequestFactory requestFactory;

	private TextView dictNameTextView;
	private TextView definitionTextView;
	private EditText wordEditText;
	private Button retrieveButton;

	private String dictionaryNamePrefix;
	private String dictionaryId;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.define);

		dictNameTextView = (TextView) findViewById(R.id.define_dictionary_name_textview);
		definitionTextView = (TextView) findViewById(R.id.define_definition_textview);
		wordEditText = (EditText) findViewById(R.id.define_word_edittext);

		retrieveButton = (Button) findViewById(R.id.define_retrieve_button);
		retrieveButton.setOnClickListener(goButtonListener);

		dictionaryId = getIntent().getStringExtra(DICT_ID_KEY);
		dictionaryNamePrefix = getString(R.string.define_dictionary_name_prefix);

		setDictionaryName(getIntent().getStringExtra(DICT_NAME_KEY));
	}

	private void setDictionaryName(String name) {
		dictNameTextView.setText(dictionaryNamePrefix + name);
	}

	private OnClickListener goButtonListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			retrieveDefinition(wordEditText.getText().toString());
		}
	};

	private void retrieveDefinition(String word) {
		setProgressBarIndeterminateVisibility(true);
		requestFactory.getDefinition(dictionaryId, word).execute(
				definitionObserver);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * Creates a dialog in the case of connection problems - this is the only
	 * dialog for this activity and hence the id is not used.
	 */
	@Override
	protected Dialog onCreateDialog(int id) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(R.string.dialog_connection_error).setPositiveButton(
				R.string.dialog_button_ok,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		return builder.create();
	}

	private SOAPObserver<Definition> definitionObserver = new SOAPObserver<Definition>() {
		@Override
		public void onCompletion(Request<Definition> request) {
			String definition;

			if (request.getResult() != null) {
				definition = request.getResult().getWordDefinition();
			} else {
				definition = getString(R.string.define_no_result_message);
			}

			definitionTextView.setText(definition);
			setProgressBarIndeterminateVisibility(false);
		}

		@Override
		public void onException(Request<Definition> request, SOAPException e) {
			Log.e(DefineActivity.class.getSimpleName(), e.getMessage(), e);
			showDialog(0);
		}
	};
}
