class Parent{
    public void display(){
        System.out.println("This is Parent Class");
    }
}
class Child extends Parent{
    public void display(){
        System.out.println("This is Child Class");
    }
    public void display1(){
        System.out.println("I am accesible via Class");
    }
     public static void main(String[] args) {
        Parent p= new Child();
        p.display();
      
     }
}