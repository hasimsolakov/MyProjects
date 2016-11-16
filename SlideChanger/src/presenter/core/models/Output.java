package presenter.core.models;


public class Output<T> implements presenter.interfaces.Output {
    private T data;

    public Output(T data){
        this.data = data;
    }

    @Override
    public T getData() {
        return this.data;
    }
}
