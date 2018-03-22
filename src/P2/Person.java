package P2;

class Person {

    // abstract function
    // the Person is the represent of one real person
    // the name in Person is the real name of the real people

    // rep invariant
    // the name can't be null

    // Safety from rep exposure:
    // set the name to private, and it can't can't be changed

    final private String name;

    Person(String name) {
        this.name = name;
        checkRep();
    }

    String getName() {
        return name;
    }

    private void checkRep() {
        assert (!(name == null));
    }
}
