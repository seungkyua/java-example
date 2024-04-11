package jiyoon;

class TV {
    private int size;
    public TV(int size) {
        this.size = size;
    }
    protected int getSize() {
        return size;
    }
}

class ColorTV extends TV {
    private int inch;
    public ColorTV(int inch, int size) {
        super(size);
        this.inch = inch;
    }

    public void printProperty() {
        System.out.println(inch + "인치 " + getSize() + "컬러");
    }

    public static void main(String[] args) {
        ColorTV myTV = new ColorTV(32, 1024);
        myTV.printProperty();
    }
}


