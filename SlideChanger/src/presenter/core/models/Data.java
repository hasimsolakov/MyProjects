package presenter.core.models;


public class Data<T> implements presenter.interfaces.Data {
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
