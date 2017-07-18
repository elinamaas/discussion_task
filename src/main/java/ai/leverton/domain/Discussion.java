package ai.leverton.domain;

import java.util.ArrayList;
import java.util.List;

public class Node<T> {

    private List<Node<T>> children = new ArrayList<>();
    private Node<T> parent = null;
    private T msg = null;

    public Node(T msg) {
        this.msg = msg;
    }

    public Node(T msg, Node<T> parent) {
        this.msg = msg;
        this.parent = parent;
    }

    public List<Node<T>> getChildren() {
        return children;
    }

    public void setParent(Node<T> parent) {
        parent.addChild(this);
        this.parent = parent;
    }

    public void addChild(T data) {
        Node<T> child = new Node<T>(data);
        child.setParent(this);
        this.children.add(child);
    }

    public void addChild(Node<T> child) {
        child.setParent(this);
        this.children.add(child);
    }

    public T getMsg() {
        return this.msg;
    }

    public void setMsg(T msg) {
        this.msg = msg;
    }

    public boolean isRoot() {
        return (this.parent == null);
    }

    public boolean isLeaf() {
        if(this.children.size() == 0)
            return true;
        else
            return false;
    }

    public void removeParent() {
        this.parent = null;
    }

}
