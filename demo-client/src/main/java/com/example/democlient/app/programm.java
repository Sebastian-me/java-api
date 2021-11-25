package com.example.democlient.app;

import com.example.democlient.dao.PersonDao;
import com.example.democlient.model.Person;
import com.example.democlient.model.PersonList;

public class programm {
    public static void main(String[] args) {
        PersonDao dao = new PersonDao();

        Person person = dao.read(2).getBody();
        assert person != null;
        System.out.println("read");
        System.out.println(person.toString());

        Person per = new Person();
        per.setFirstname("mr");
        per.setLastname("tee");
        Person cperson = dao.create(per).getBody();
        System.out.println("create");
        System.out.println(cperson.toString());

        PersonList pList = dao.readAll().getBody();
        System.out.println("all");
        assert pList != null;
        for (Person pe : pList.getPersonList()) {
            System.out.println(pe.toString());
        }


        Person p = new Person();
        p.setId(1);
        p.setFirstname("peter");
        p.setLastname("griffin");

        Person updatedP = dao.update(p).getBody();
        System.out.println("update");
        System.out.println(updatedP.toString());

        Person dp = dao.delete(2).getBody();

        System.out.println("delete");
        System.out.println(dp.toString());
    }
}
