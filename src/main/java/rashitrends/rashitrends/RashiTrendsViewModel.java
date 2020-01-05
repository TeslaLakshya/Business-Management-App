package rashitrends.rashitrends;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class RashiTrendsViewModel extends AndroidViewModel {

    RashiTrendsRepository repository;
    public RashiTrendsViewModel(@NonNull Application application) {
        super(application);
        repository = new RashiTrendsRepository(application);
    }

    //Insert Wrappers
    public void insert(Customers customer) {
        repository.insert(customer);
    }

    public void insert(Items item) {
        repository.insert(item);
    }

    public void insert(Employees employee) {
        repository.insert(employee);
    }

    public void insert(Orders order) {
        repository.insert(order);
    }

    //Update Wrappers
    public void update(Customers customer) {
        repository.update(customer);
    }

    public void update(Items item) {
        repository.update(item);
    }

    public void update(Employees employee) {
        repository.update(employee);
    }

    public void update(Orders order) {
        repository.update(order);
    }

    //Delete Wrappers
    public void delete(Customers customer) {
        repository.delete(customer);
    }

    public void delete(Items item) {
        repository.delete(item);
    }

    public void delete(Employees employee) {
        repository.delete(employee);
    }

    public void delete(Orders order) {
        repository.delete(order);
    }

    //Get Wrappers
    public LiveData<List<Customers>> getAllCustomers() {
        return repository.getAllCustomers();
    }

    public LiveData<List<Items>> getAllItems() {
        return repository.getAllItems();
    }

    public LiveData<List<Employees>> getAllEmployees() {
        return repository.getAllEmployees();
    }

    public LiveData<List<Orders>> getAllOrders() {
        return repository.getAllOrders();
    }
}
