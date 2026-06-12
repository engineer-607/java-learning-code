package com.nanxinda.linkedlist.singlelinkedlist;

public class HeroNode{
    private int no;
    private String name;
    private String nikeName;
    public HeroNode next;

    public HeroNode(int no, String name, String nikeName) {
        this.no = no;
        this.name = name;
        this.nikeName = nikeName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "nikeName='" + nikeName + '\'' +
                ", name='" + name + '\'' +
                ", no=" + no +
                '}';
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNikeName() {
        return nikeName;
    }

    public void setNikeName(String nikeName) {
        this.nikeName = nikeName;
    }

    public HeroNode getNext() {
        return next;
    }

    public void setNext(HeroNode next) {
        this.next = next;
    }
    public HeroNode copy(){
        return new HeroNode(no,name,nikeName);
    }
}
