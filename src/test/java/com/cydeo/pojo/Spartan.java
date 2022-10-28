package com.cydeo.pojo;

import com.cydeo.utils.SpartanTestBase;
import lombok.Data;
@Data //This will add getter/setter for each variable

public class Spartan {
    /**
     * "id": 10,
     *     "name": "Lorenza",
     *     "gender": "Female",
     *     "phone": 3312820936
     */
    private int id;
    private String name;
    private String gender;
    private long phone;

    public static void main(String[] args) {
        Spartan spartan = new Spartan();
        System.out.println(spartan.getGender());
    }
}

