package presenter.Core.Models;


import presenter.Interfaces.IOutput;


public class Output<T> implements IOutput {
    private T data;

    public Output(T data){
        this.data = data;
    }

    @Override
    public T getData() {
        return this.data;
    }
}
