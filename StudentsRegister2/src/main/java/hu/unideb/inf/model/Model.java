<<<<<<< HEAD
package hu.unideb.inf.model;

import java.time.LocalDate;

public class Model {
    private Student student;

    public Model() {
        this.student = new Student("Rob Smith",
                                   55,
                                   LocalDate.of(1991,12,03));
    }

    public Student getStudent() {
        return student;
    }
}
=======
package hu.unideb.inf.model;

import java.time.LocalDate;

public class Model {
    private Student student;

    public Model() {
        this.student = new Student("Rob Smith",
                                   55,
                                   LocalDate.of(1991,12,03));
    }

    public Student getStudent() {
        return student;
    }
}
>>>>>>> 9c321cd18c049469cbd4d980cf82919518eacf26
