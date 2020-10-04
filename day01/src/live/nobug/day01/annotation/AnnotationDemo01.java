package live.nobug.day01.annotation;

@SuppressWarnings("all")
public class AnnotationDemo01 {
    @Override
    public String toString() {
        return super.toString();
    }

    @Deprecated
    public void show1(){
        int a;
    }

    public void show2(){
//        show1();
    }
}
