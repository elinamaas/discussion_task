package ai.leverton.domain;

import java.util.ArrayList;
import java.util.List;

public class Discussion<T> {

    private List<Discussion<T>> children = new ArrayList<>();
    private Discussion<T> parent = null;
    private T msg = null;

    public Discussion(T msg) {
        this.msg = msg;
    }

    public Discussion(T msg, Discussion<T> parent) {
        this.msg = msg;
        this.parent = parent;
    }

    public List<Discussion<T>> getChildren() {
        return children;
    }

    public void setParent(Discussion<T> parent) {
//        parent.addChild(this);
        this.parent = parent;
    }

    public void addChild(T data) {
        Discussion<T> child = new Discussion<T>(data);
        child.setParent(this);
        this.children.add(child);
    }

    public void addChild(Discussion<T> child) {
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
        if (this.children.size() == 0)
            return true;
        else
            return false;
    }

    public void removeParent() {
        this.parent = null;
    }

    public void removeChild(T msgToRemove) {
        T msg = this.getMsg();
        if (msg.equals(msgToRemove)) {
            this.removeParent();
        } else {
            List<Discussion<T>> children = this.getChildren();
            children.forEach(discussion -> this.removeChild(msgToRemove));

        }


    }

}
