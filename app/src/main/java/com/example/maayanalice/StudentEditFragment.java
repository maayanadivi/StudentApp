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
import static com.example.maayanalice.StudentListFragment.adapter;

public class StudentEditFragment extends Fragment {
    Student student;
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_edit, container, false);
        EditText name = view.findViewById(R.id.student_name_field);
        EditText id = view.findViewById(R.id.student_id_field);
        id.setEnabled(false);
        EditText address = view.findViewById(R.id.student_address_field);
        EditText phone = view.findViewById(R.id.student_phone_field);
        CheckBox cb = view.findViewById(R.id.student_checkBox);

        progressBar = view.findViewById(R.id.details_progressbar);
        progressBar.setVisibility(View.GONE);

        Button save_btn =view.findViewById(R.id.editStudent_save);
        Button cancel_btn =view.findViewById(R.id.editStudent_cancel);
        Button delete_btn =view.findViewById(R.id.editStudent_delete);

        String studentId = StudentDetailsFragmentArgs.fromBundle(getArguments()).getStudentId();
        Model.instance.getStudentById(studentId, (student1) -> {
            student = student1;
            name.setText(student.getName());
            id.setText(student.getId());
            address.setText(student.getAddress());
            phone.setText(student.getPhone());
            cb.setChecked(student.isFlag());
        });

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                save_btn.setEnabled(false);
                cancel_btn.setEnabled(false);
                delete_btn.setEnabled(false);
                student.setName(name.getText().toString());
                student.setId(id.getText().toString());
                student.setAddress(address.getText().toString());
                student.setPhone(phone.getText().toString());
                student.setFlag(cb.isChecked());
                Model.instance.addStudent(student, () -> {
                    progressBar.setVisibility(View.GONE);
                    adapter.notifyDataSetChanged();
                    Navigation.findNavController(v).navigate(StudentEditFragmentDirections.actionStudentEditFragmentToStudentListFragment());
                });
            }
        });

        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar.setVisibility(View.VISIBLE);
                save_btn.setEnabled(false);
                cancel_btn.setEnabled(false);
                delete_btn.setEnabled(false);
                Navigation.findNavController(view).navigate(StudentEditFragmentDirections.actionStudentEditFragmentToStudentListFragment());
            }
        });

        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                save_btn.setEnabled(false);
                cancel_btn.setEnabled(false);
                delete_btn.setEnabled(false);
                Model.instance.deleteStudent(student, new Model.DeleteStudentListener() {
                    @Override
                    public void onComplete(int status) {
                        adapter.notifyDataSetChanged();
                        Navigation.findNavController(v).navigate(StudentEditFragmentDirections.actionStudentEditFragmentToStudentListFragment());
                    }
                });
            }
        });

        return view;
    }

    private void updateStudent(Student s) {
        student.setName(s.getName()+"");
        student.setId(s.getId()+"");
        student.setAddress(s.getAddress()+"");
        student.setPhone(s.getPhone()+"");
        student.setFlag(s.isFlag());
        progressBar.setVisibility(View.GONE);
    }
}