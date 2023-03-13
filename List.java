public class List<T> {
    public int length;
    public Node<T> head;

    public List(){
        this.length = 0;
        this.head = null;
    }

    public String toString(){
        String tempString = "";
        if(this.length == 0 || this.head == null){
            return tempString;
        }
        tempString += this.head.toString();
        for(Node<T> tempNode = this.head.next; tempNode != null; tempNode = tempNode.next){
            tempString += "," + tempNode.toString();
        }
        return tempString;
    }

    public void append(T val){
        Node<T> insertNode = new Node<T>(val);
        if(this.length == 0 && this.head == null){
            this.head = insertNode;
            this.length += 1;
        }
        else {
            Node<T> nodePtr = this.head;
            while(nodePtr.next != null){
                nodePtr = nodePtr.next;
            }
            nodePtr.next = insertNode;
            this.length += 1;
        }
    }

    public boolean remove(T val){
        Node<T> previousNode = null;
        Node<T> nodePtr = this.head;
        if(this.head == null || this.length == 0){
            return false;
        }
        while(nodePtr != null && nodePtr.data.equals(val) == false){
            previousNode = nodePtr;
            nodePtr = nodePtr.next;
        }
        if(previousNode == null && this.head.data.equals(val) == true){
            this.head = this.head.next;
            this.length -= 1;
            return true;
        }
        else if(nodePtr != null && previousNode != null  && nodePtr.data.equals(val) == true){
            previousNode.next = nodePtr.next;
            this.length -= 1;
            return true;
        }
        return false;
    }

    public boolean remove(List<T> val){
        int counter = 0;
        Node<T> tempCurrent = val.head;
        while(tempCurrent != null){
            boolean removed = this.remove(tempCurrent.data);
            if(removed == true){
                counter += 1;
            }
            tempCurrent = tempCurrent.next;
        }
        if(counter >= 1){
            return true;
        }
        return false;
    }

    public boolean contains(T search){
        if(this.head != null){
            Node<T> tempListHead = this.head;
            while(tempListHead != null){
                if(tempListHead.data.equals(search) == true){
                    return true;
                }
                tempListHead = tempListHead.next;
            }
        }
        return false;
    }

    public boolean equals(List<T> other){
        if(this.length != other.length){
            return false;
        }
        int counter = 0;
        Node<T> tempCurrent = other.head;
        Node<T> tempCurrent2 = this.head;
        while(tempCurrent != null && tempCurrent2 != null){
            if(this.contains(tempCurrent.data) == false || tempCurrent.data.equals(tempCurrent2.data) == false){
                return false;
            }
            counter += 1;
            tempCurrent = tempCurrent.next;
            tempCurrent2 = tempCurrent2.next;
        }
        if(counter != this.length){
            return false;
        }
        return true;
    }
}