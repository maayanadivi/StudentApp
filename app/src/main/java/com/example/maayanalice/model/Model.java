package com.example.maayanalice.model;

import com.example.maayanalice.MyApplication;

import java.util.List;

public class Model {
    public static final Model instance = new Model();

    private Model(){
    }

    public interface GetAllStudentsListener{
        void onComplete(List<Student> data);
    }
    public void getAllStudents(GetAllStudentsListener listener){
        MyApplication.executorService.execute(()->{
            List<Student> data = AppLocalDB.db.studentDao().getAll();
            MyApplication.mainHandler.post(()->{
                listener.onComplete(data);
            });
        });
    }

    public interface AddStudentListener{
        void onComplete();
    }
    public void addStudent(Student student, AddStudentListener listener){
        MyApplication.executorService.execute(()->{
            try{
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            AppLocalDB.db.studentDao().insertAll(student);
            MyApplication.mainHandler.post(()->{
                listener.onComplete();
            });
        });
    }

    public interface GetStudentByIdListener{
        void onComplete(Student student);
    }
    public void getStudentById(String studentId,GetStudentByIdListener listener) {
        MyApplication.executorService.execute(()->{
            Student student = AppLocalDB.db.studentDao().getStudentById(studentId);
            MyApplication.mainHandler.post(()->{
                listener.onComplete(student);
            });
        });
    }
    public interface DeleteStudentListener{

        void onComplete(int status);
    }
    public void deleteStudent(Student student, DeleteStudentListener listener) {
        MyApplication.executorService.execute((() -> {
            try{
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            AppLocalDB.db.studentDao().delete(student);
            MyApplication.mainHandler.post(() -> {
                listener.onComplete(1);
            });
        }));
    }

}
