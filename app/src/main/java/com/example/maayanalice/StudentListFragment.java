package com.example.maayanalice;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.maayanalice.model.Model;
import com.example.maayanalice.model.Student;

import java.util.List;


public class StudentListFragment extends Fragment {
    List<Student> studentList;
    View view;
    CheckBox studentCb;
    static public MyAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //studentCb = view.findViewById(R.id.student_details_cb);
        adapter = new MyAdapter();
        view = inflater.inflate(R.layout.fragment_student_list, container, false);
        Model.instance.getAllStudents(new Model.GetAllStudentsListener() {
            @Override
            public void onComplete(List<Student> data) {
                studentList = data;
                adapter.notifyDataSetChanged();
            }
        });

        RecyclerView list = view.findViewById(R.id.student_list_row);
        list.setHasFixedSize(true);

        list.setLayoutManager(new LinearLayoutManager(getContext()));

        list.setAdapter(adapter);




        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Student st = studentList.get(position);
                Log.d("TAG","row was clicked " + position);

                StudentListFragmentDirections.ActionStudentListFragmentToStudentDetailsFragment action =
                        StudentListFragmentDirections.actionStudentListFragmentToStudentDetailsFragment(st.getId());
                Navigation.findNavController(v).navigate(action);
            }
        });
        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public void setHasOptionsMenu(boolean hasMenu) {
        super.setHasOptionsMenu(hasMenu);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.student_list_menu,menu);
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_add:
                Navigation.findNavController(view).navigate(StudentListFragmentDirections.actionStudentListFragmentToAddStudentFragment());
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView nameTv;
        TextView idTv;
        CheckBox cb;



        public MyViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.list_row_name_tv);
            idTv = itemView.findViewById(R.id.list_row_id_tv);
            cb = itemView.findViewById(R.id.list_row_cb);

            cb.setOnClickListener(view -> {
                cb.setEnabled(false);
                int pos = getAdapterPosition();
                Student student = studentList.get(pos);
                Student newStudent = new Student(student.getName(), student.getId(), !student.isFlag(), student.getPhone(), student.getAddress());
                Model.instance.addStudent(newStudent, () -> {
                    cb.setEnabled(true);
                    adapter.notifyDataSetChanged();
                });
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (listener != null) {
                        listener.onItemClick(pos,v);
                    }
                }
            });
        }
    }

    interface OnItemClickListener{
        void onItemClick(int position, View v);
    }
    class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{

        OnItemClickListener listener;
        public void setOnItemClickListener(OnItemClickListener listener){
            this.listener = listener;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.student_list_row,parent,false);
            MyViewHolder holder = new MyViewHolder(view,listener);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            Student student = studentList.get(position);
            holder.nameTv.setText(student.getName());
            holder.idTv.setText(student.getId());
            holder.cb.setChecked(student.isFlag());
        }

        @Override
        public int getItemCount() {
            if(studentList != null) {
                return studentList.size();
            } else {
                return -1;
            }
        }
    }
}