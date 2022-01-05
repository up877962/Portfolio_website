/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myqueue;

/**
 *
 * @author pompe
 */
public class MyQueue {

    private int capacity = 3;
    private int arr[] = new int[capacity];
    private int size = 0;
    private int top = -1;
    private int rear = 0;
    private int counter = 0;

    public void push(int pushedElement) {
        if (top < capacity - 1) {
            top++;
            arr[top] = pushedElement;
            counter++;
            System.out.println("Element " + pushedElement
                    + " is pushed to Queue !");
            display();
        } else {
            System.out.println("Overflow !");
        }

    }

    public void pop() {
        if (top >= rear) {
            rear++;
            counter--;
            System.out.println("Pop operation done !");
            display();
        } else {
            System.out.println("Underflow !");
        }
    }

    public void count() {

        System.out.println("number of elements: " + counter);

    }

    public void display() {
        if (top >= rear) {
            System.out.println("Elements in Queue : ");
            for (int i = rear; i <= top; i++) {
                System.out.println(arr[i]);
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        myQueue.pop();
        myQueue.push(23);
        myQueue.push(2);
        myQueue.push(73);
        myQueue.push(21);
//        myQueue.pop();
//        myQueue.pop();
//        myQueue.pop();
//        myQueue.pop();
        myQueue.display();
        myQueue.count();
        myQueue.pop();
        myQueue.count();
    }

}
