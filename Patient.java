public class Patient {
    String name, dob, gender, bld;
    int age, height, weight;

    Patient(String n, String a, String d, String g, int h, int w, String b) {
        this.name = n;
        this.age = Integer.parseInt(a);
        this.dob = d;
        this.gender = g;
        this.height = h;
        this.weight = w;
        this.bld = b;
    }

    @Override
    public String toString() {
        String res = "<html><h3>Found Record!</h3> <br> Name : " + name + "<br>" + "Age : " + age + "<br>" + "DOB : "
                + dob + "<br>" + "Gender : " + gender + "<br>" + "Year : " + height + "<br>" + "Month : " + weight
                + "<br>" + "Blood Group : " + bld + "<br></html>";
        return res;
    }
}