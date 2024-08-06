 package com.example.afya_app;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AddNewReminder extends BottomSheetDialogFragment {

    public static final String TAG = "AddNewReminder";

    private TextView dateTextView;
    private EditText reminder_edittext;
    private Button buttonSave;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String dueDate = "";
    private Context context;
    private String id = "";
    private String dueDateUpdate="";

    public static AddNewReminder newInstance() {
        return new AddNewReminder();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_new_reminder, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dateTextView = view.findViewById(R.id.dateTextView);
        buttonSave = view.findViewById(R.id.buttonSave);
        reminder_edittext = view.findViewById(R.id.reminder_edittext);

        boolean isUpdate = false;

        final Bundle bundle = getArguments();
        if(bundle!=null){
            isUpdate = true;
            String reminder= bundle.getString("title");
            id = bundle.getString("id");
            dueDateUpdate=bundle.getString("due");

            reminder_edittext.setText(reminder);
            dateTextView.setText(dueDate);

            if (reminder.length()>0){
                buttonSave.setEnabled(false);
                buttonSave.setBackgroundColor(Color.GRAY);

            }
        }


        reminder_edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")) {
                    buttonSave.setEnabled(false);
                    buttonSave.setBackgroundColor(Color.GRAY);
                } else {
                    buttonSave.setEnabled(true);
                    buttonSave.setBackgroundColor(getResources().getColor(R.color.periwinkle));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        dateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();

                int MONTH = calendar.get(Calendar.MONTH);
                int YEAR = calendar.get(Calendar.YEAR);
                int DAY = calendar.get(Calendar.DATE);

                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        dateTextView.setText(dayOfMonth + "/" + month + "/" + year);
                        dueDate = dayOfMonth + "/" + month + "/" + year;
                    }
                }, YEAR, MONTH, DAY);
                datePickerDialog.show();
            }
        });

        boolean finalIsUpdate = isUpdate;
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = reminder_edittext.getText().toString();

                if(finalIsUpdate){
                    db.collection("reminder").document(id).update("title", title, "due",dueDate);
                    Toast.makeText(context, "Reminder Updated", Toast.LENGTH_SHORT).show();
                }else {
                    if (title.isEmpty()) {
                        Toast.makeText(context, "Empty title is not allowed!", Toast.LENGTH_SHORT).show();
                    } else {
                        Map<String, Object> reminderMap = new HashMap<>();

                        reminderMap.put("title", title);
                        reminderMap.put("due", dueDate);
                        reminderMap.put("status", 0);
                        reminderMap.put("time", FieldValue.serverTimestamp());

                        db.collection("reminder").add(reminderMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(context, "Reminder Saved", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(context, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
                dismiss();
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        Activity activity = getActivity();
        if (activity instanceof OnDialogCloseListener) {
            ((OnDialogCloseListener) activity).OnDialogClose(dialog);
        }
    }
}
