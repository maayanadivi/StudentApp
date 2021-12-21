package com.example.maayanalice;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.maayanalice.model.Model;
import com.example.maayanalice.model.Student;


public class AddStudentFragment extends Fragment {

    EditText name;
    EditText id;
    EditText phone;
    EditText address;
    CheckBox cb;
    Button save_btn;
    Button cancel_btn;
    View view;
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_add_student, container, false);

        name = view.findViewById(R.id.addStudent_name_field);
        id = view.findViewById(R.id.addStudent_id_field);
        address = view.findViewById(R.id.addstudent_address_field);
        phone = view.findViewById(R.id.addstudent_phone_field);
        cb = view.findViewById(R.id.addstudent_checkBox);

        progressBar = view.findViewById(R.id.details_progressbar);
        progressBar.setVisibility(View.GONE);

        save_btn =view.findViewById(R.id.addStudent_save);
        cancel_btn =view.findViewById(R.id.addStudent_cancel);

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                save_btn.setEnabled(false);
                cancel_btn.setEnabled(false);
                Student student = new Student();
                student.setName(name.getText()+"");
                student.setId(id.getText()+"");
                student.setAddress(address.getText()+"");
                student.setPhone(phone.getText()+"");
                student.setFlag(cb.isChecked());

                Model.instance.addStudent(student,()->{
                    Navigation.findNavController(view).navigateUp();
                });
            }
        });

        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigateUp();
            }
        });

        return view;
    }

    private void save() {
        //progressbar.setVisibility(View.VISIBLE);
        save_btn.setEnabled(false);
        cancel_btn.setEnabled(false);

        Student student = new Student();
        student.setName(name.getText()+"");
        student.setId(id.getText()+"");
        student.setAddress(address.getText()+"");
        student.setPhone(phone.getText()+"");
        student.setFlag(cb.isChecked());

//        Log.d("TAG","saved name:" + name + " id:" + id + " flag:" + flag);

        Model.instance.addStudent(student,()-> {
            Navigation.findNavController(view).navigateUp();
        });
    }

}