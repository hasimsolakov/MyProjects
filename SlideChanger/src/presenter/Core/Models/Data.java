package presenter.Core.Models;


import presenter.Interfaces.IData;

public class Data<T> implements IData {
    private T data;

    public Data(){

    }

    public Data(T data){
        this.data = data;
    }

    @Override
    public T getData() {
        return this.data;
    }
}
