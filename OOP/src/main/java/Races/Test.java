package Races;

import javax.persistence.*;

@Entity
@Table(name="test")
public class Test
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="surname")
    private String surname;

    @Column(name="score")
    private int score = 0;

    public Test() {}

    public Test(String _name, String _surname)
    {
        name = _name;
        surname = _surname;
    }

    @Override
    public String toString()
    {
        return id + " " + name + " " + surname + ", score: " + score;
    }
}