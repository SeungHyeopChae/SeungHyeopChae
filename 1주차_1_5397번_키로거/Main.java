package com.ssafy.list;

import java.util.Scanner;

class LinkedList {
	private Node head;
    private Node tail;
    private int size;

    static class Node{
        private Object data;
        private Node next;

        public Node(Object data) {
            this.data = data;
            this.next = null;
        }    

        public String toString(){
            return String.valueOf(this.data);
        }
    }
    
    public void addFirst(Object data){
        Node newNode = new Node(data);

        newNode.next = head;
        head=newNode;

        size++;
        if(head.next == null){
            tail = head;
        }
    }
       
    public void addLast(Object data){
        Node newNode = new Node(data);

        if(size == 0){
            addFirst(data);
        }
        else {
            tail.next = newNode;
            tail = newNode;
            size++;
        }
    }
    
    public Node node(int index) {
        Node x = head;
        for (int i = 0; i < index; i++)
            x = x.next;
        return x;
    }
    
    
    public void addMiddle(Object data, int idx){

        if(idx == 0){
            addFirst(data);
        }
        else{
            Node tmp_1 = node(idx-1);
            Node tmp_2 = tmp_1.next;
            Node newNode = new Node(data);

            tmp_1.next = newNode;
            newNode.next = tmp_2;
            size++;

            if(newNode.next == null){
                tail = newNode;
            }
        }
    }
    
    public Object remove(int idx){
        if(idx == 0){
            return removeFirst();
        }
        Node tmp = node(idx-1);
        Node willDelete = tmp.next;
        tmp.next = tmp.next.next;

        Object data = willDelete.data;
        if(willDelete == tail){
            tail = tmp;
        }
        willDelete = null;
        size--;

        return data;
    }
      
    public Object removeFirst(){

        Node tmp = head;
        head = head.next;
        Object data = tmp.data;
        tmp = null;
        size--;
        return data;
    }
      
    public Object removeLast(){

        Node tmp = tail;
        tail = node(size-2);
        tail.next=null;
        Object data = tmp.data;
        tmp = null;
        size--;
        return data;
    }
     
    public Object getNode(int idx){
        return node(idx).data;
    }

    public Object getFirst(){
        return head.data;
    }

    public Object getLast(){
        return tail.data;
    }
    
    public int getIndex(Object data){
        Node tmp = head;
        int idx = 0;
        while(tmp.data != data){
            tmp = tmp.next;
            idx++;
            if(tmp == null)
                return -1;
        }
        return idx;
    }  
    
    public boolean isEmpyt(){
        if(size == 0)
            return true;
        return false;
    }

    public int getSize(){
        return size;
    }

    public void clear(){
        while(size>0){
            removeFirst();
        }
    }    
    
    public String toString(){
        if(head == null){
            return null;
        }
        Node tmp = head;
        String str = "";
        while(tmp.next != null){
            str += tmp.data;
            tmp = tmp.next;
        }

        str+= tmp.data;
        return str;
    }
}


public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int testCase = sc.nextInt();
		String[] rst = new String[testCase];
		
		for(int i = 0; i < testCase; i++)
		{
			LinkedList linkedList = new LinkedList();    // 연결 리스트 생성
			String[] s = sc.next().split("");
			char[] arr = new char[s.length];
			int cnt = 0;
			for(int j = 0; j < s.length; j++)
			{		
				arr[j] = s[j].charAt(0);
				if(arr[j] == '-')
				{
					//삭제
					if(cnt < 0) {
						int num = linkedList.getSize() - 1 + cnt;
						linkedList.remove(num);
						continue;				
					}
					else 
					{
						linkedList.removeLast();
						continue;
					}
					
				}
				else if(arr[j] == '<')
				{
					if(linkedList.getSize() != 0 && linkedList.getSize() - cnt > 0) 
					{
						cnt--;
						continue;
					}
				}
				else if(arr[j] == '>')
				{
					if(linkedList.getSize() != 0 && linkedList.getSize() != linkedList.getIndex(linkedList.getLast()))
					{
						cnt++;
						continue;
					}
				}
				else
				{
					if(cnt < 0) {
						int num = linkedList.getSize() + cnt;
						linkedList.addMiddle(s[j],num);
					}
					else 
						linkedList.addLast(s[j]);
				}
			}   		 
			rst[i]	= linkedList.toString();	
		}
		sc.close();
		for(int k = 0; k < testCase; k++)
		{
			System.out.println(rst[k]);
		}		
	}
}
