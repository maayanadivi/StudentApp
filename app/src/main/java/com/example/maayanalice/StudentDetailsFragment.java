package com.example.maayanalice;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.maayanalice.model.Model;
import com.example.maayanalice.model.Student;
import static com.example.maayanalice.StudentListFragment.adapter;

public class StudentDetailsFragment extends Fragment {
    Student curStudent;

    TextView studentNameTxt;
    TextView studentIdTxt;
    TextView studentAddressTxt;
    TextView studentPhoneTxt;
    Button editBtn;
    CheckBox studentCb;
    View view;
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_student_details, container, false);
        studentNameTxt = view.findViewById(R.id.sd_name_tv);
        studentIdTxt = view.findViewById(R.id.sd_id_tv);
        studentAddressTxt = view.findViewById(R.id.sd_address_tv);
        studentPhoneTxt = view.findViewById(R.id.sd_phone_tv);
        studentCb = view.findViewById(R.id.student_details_cb);
        editBtn = view.findViewById(R.id.sd_edit_btn);
        progressBar = view.findViewById(R.id.details_progressbar);
        progressBar.setVisibility(View.GONE);
        String studentId = StudentDetailsFragmentArgs.fromBundle(getArguments()).getStudentId();

        Model.instance.getStudentById(studentId, (student1 -> {
            updateDisplay(student1);
        }));

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar.setVisibility(View.VISIBLE);
                editBtn.setEnabled(false);
                StudentDetailsFragmentDirections.ActionStudentDetailsFragmentToStudentEditFragment action =
                        StudentDetailsFragmentDirections.actionStudentDetailsFragmentToStudentEditFragment(studentId);
                Navigation.findNavController(v).navigate(action);
            }
        });

        studentCb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentCb.setEnabled(false);
                Student st = new Student(curStudent.getName(),curStudent.getId(),!(curStudent.isFlag()),curStudent.getPhone(),curStudent.getAddress());
                Model.instance.addStudent(st, () -> {
                    studentCb.setEnabled(true);
                    adapter.notifyDataSetChanged();
                });
            }
        });

        return view;
    }
    private void updateDisplay(Student student) {
        curStudent = student;
        studentNameTxt.setText(student.getName());
        studentIdTxt.setText(student.getId());
        studentAddressTxt.setText(student.getAddress());
        studentPhoneTxt.setText(student.getPhone());
        studentCb.setChecked(student.isFlag());
    }
}