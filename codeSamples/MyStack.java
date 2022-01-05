/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mystack;

/**
 * test functionality/change methods to return values and remove print
 * statements for proper usage
 *
 * @author pompe
 */
public class MyStack {

    private int capacity = 50;
    private int arr[] = new int[capacity];
    private int top = -1;

    public void push(int pushedElement) {
        if (top < capacity - 1) {
            top++;
            arr[top] = pushedElement;
            System.out.println("Element " + pushedElement
                    + " is pushed to Stack");
            printElements();
        } else {
            System.out.println("Stack full");
        }
    }

    public void pop() {
        if (top >= 0) {
            top--;
            System.out.println("Pop operation done");
        } else {
            System.out.println("Stack empty");
        }
    }

    public void peek() {
        System.out.println("the top element is: " + arr[top]);
    }

    public void size() {
        System.out.println("stack size is " + (top + 1));
    }

    public boolean isFull() {
        return top == capacity - 1;

    }

    public boolean isEmpty() {
        return top == capacity - 1;

    }

    public void printElements() {
        if (top >= 0) {
            System.out.println("Elements in stack :");
            for (int i = 0; i <= top; i++) {
                System.out.println(arr[i]);

            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MyStack myStack = new MyStack();

        myStack.pop();
        myStack.push(23);
        myStack.push(2);
        myStack.push(73);
        myStack.push(21);

        myStack.peek();
        myStack.pop();
        myStack.pop();
        myStack.pop();
        myStack.peek();
//  myStack.pop();
        myStack.size();
        if (myStack.isFull()) {
            System.out.println("stack is full");
        } else {
            System.out.println("stack is not full");
        }

        if (myStack.isEmpty()) {
            System.out.println("stack is Empty");
        } else {
            System.out.println("stack is not Empty");
        }
    }

}
