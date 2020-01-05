package rashitrends.rashitrends;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.ClipData;
import android.os.AsyncTask;

import java.util.List;

public class RashiTrendsRepository {

    private OrderDao orderDao;
    private CustomerDao customerDao;
    private ItemDao itemDao;
    private EmployeeDao employeeDao;

    public RashiTrendsRepository(Application application) {
        orderDao = RashiTrendsDatabase.getInstance(application).getOrderDao();
        customerDao = RashiTrendsDatabase.getInstance(application).getCustomerDao();
        itemDao = RashiTrendsDatabase.getInstance(application).getItemDao();
        employeeDao = RashiTrendsDatabase.getInstance(application).getEmployeeDao();
    }

    //Insert part
    //Orders
    public void insert(Orders order) {
        new InsertOrdersAsyncTask(orderDao).execute(order);
    }

    public class InsertOrdersAsyncTask extends AsyncTask<Orders, Void, Void> {
        private OrderDao orderDao;
        public InsertOrdersAsyncTask(OrderDao orderDao) {
            this.orderDao = orderDao;
        }
        @Override
        protected Void doInBackground(Orders... orders) {
            orderDao.insert(orders[0]);
            return null;
        }
    }

    //Customers

    public void insert(Customers customer) {
        new InsertCustomersAsyncTask(customerDao).execute(customer);
    }

    public class InsertCustomersAsyncTask extends AsyncTask<Customers, Void, Void> {
        private CustomerDao customerDao;
        public InsertCustomersAsyncTask(CustomerDao customerDao) {
            this.customerDao = customerDao;
        }
        @Override
        protected Void doInBackground(Customers... customers) {
            customerDao.insert(customers[0]);
            return null;
        }
    }

    //Employees

    public void insert(Employees employee) {
        new InsertEmployeesAsyncTask(employeeDao).execute(employee);
    }

    public class InsertEmployeesAsyncTask extends AsyncTask<Employees, Void, Void> {
        private EmployeeDao employeeDao;
        public InsertEmployeesAsyncTask(EmployeeDao employeeDao) {
            this.employeeDao = employeeDao;
        }
        @Override
        protected Void doInBackground(Employees... employees) {
            employeeDao.insert(employees[0]);
            return null;
        }
    }

    //Items

    public void insert(Items items) {
        new InsertItemAsyncTask(itemDao).execute(items);
    }

    public class InsertItemAsyncTask extends AsyncTask<Items, Void, Void> {
        private ItemDao itemDao;
        public InsertItemAsyncTask(ItemDao itemDao) {
            this.itemDao = itemDao;
        }
        @Override
        protected Void doInBackground(Items... items) {
            itemDao.insert(items[0]);
            return null;
        }
    }

    //Update
    //Customers

    public void update(Customers customer) {
        new UpdateCustomersAsyncTask(customerDao).execute(customer);
    }

    public class UpdateCustomersAsyncTask extends AsyncTask<Customers, Void, Void> {
        private CustomerDao customerDao;
        public UpdateCustomersAsyncTask(CustomerDao customerDao) {
            this.customerDao = customerDao;
        }
        @Override
        protected Void doInBackground(Customers... customers) {
            customerDao.update(customers[0]);
            return null;
        }
    }

    //Employees

    public void update(Employees employee) {
        new UpdateEmployeesAsyncTask(employeeDao).execute(employee);
    }

    public class UpdateEmployeesAsyncTask extends AsyncTask<Employees, Void, Void> {
        private EmployeeDao employeeDao;
        public UpdateEmployeesAsyncTask(EmployeeDao employeeDao) {
            this.employeeDao = employeeDao;
        }
        @Override
        protected Void doInBackground(Employees... employees) {
            employeeDao.update(employees[0]);
            return null;
        }
    }

    //Orders

    public void update(Orders order) {
        new UpdateOrderAsyncTask(orderDao).execute(order);
    }

    public class UpdateOrderAsyncTask extends AsyncTask<Orders, Void, Void> {
        private OrderDao orderDao;
        public UpdateOrderAsyncTask(OrderDao orderDao) {
            this.orderDao = orderDao;
        }
        @Override
        protected Void doInBackground(Orders... orders) {
            orderDao.update(orders[0]);
            return null;
        }
    }

    //Items

    public void update(Items items) {
        new UpdateItemsAsyncTask(itemDao).execute(items);
    }

    public class UpdateItemsAsyncTask extends AsyncTask<Items, Void, Void> {
        private ItemDao itemDao;
        public UpdateItemsAsyncTask(ItemDao itemDao) {
            this.itemDao = itemDao;
        }
        @Override
        protected Void doInBackground(Items... items) {
            itemDao.update(items[0]);
            return null;
        }
    }

    //delete
    //Customers

    public void delete(Customers customer) {
        new DeleteCustomersAsyncTask(customerDao).execute(customer);
    }

    public class DeleteCustomersAsyncTask extends AsyncTask<Customers, Void, Void> {
        private CustomerDao customerDao;
        public DeleteCustomersAsyncTask(CustomerDao customerDao) {
            this.customerDao = customerDao;
        }
        @Override
        protected Void doInBackground(Customers... customers) {
            customerDao.delete(customers[0]);
            return null;
        }
    }

    //Employees

    public void delete(Employees employee) {
        new DeleteEmployeesAsyncTask(employeeDao).execute(employee);
    }

    public class DeleteEmployeesAsyncTask extends AsyncTask<Employees, Void, Void> {
        private EmployeeDao employeeDao;
        public DeleteEmployeesAsyncTask(EmployeeDao employeeDao) {
            this.employeeDao = employeeDao;
        }
        @Override
        protected Void doInBackground(Employees... employees) {
            employeeDao.delete(employees[0]);
            return null;
        }
    }

    //Orders

    public void delete(Orders order) {
        new DeleteOrdersAsyncTask(orderDao).execute(order);
    }

    public class DeleteOrdersAsyncTask extends AsyncTask<Orders, Void, Void> {
        private OrderDao orderDao;
        public DeleteOrdersAsyncTask(OrderDao orderDao) {
            this.orderDao = orderDao;
        }
        @Override
        protected Void doInBackground(Orders... orders) {
            orderDao.delete(orders[0]);
            return null;
        }
    }

    //Items
    public void delete(Items item) {
        new DeleteItemsAsyncTask(itemDao).execute(item);
    }

    public class DeleteItemsAsyncTask extends AsyncTask<Items, Void, Void> {
        private ItemDao itemDao;
        public DeleteItemsAsyncTask(ItemDao itemDao) {
            this.itemDao = itemDao;
        }
        @Override
        protected Void doInBackground(Items... items) {
            itemDao.delete(items[0]);
            return null;
        }
    }

    //Get Data Wrappers


    public LiveData<List<Orders>> getAllOrders() {
        return orderDao.getAllOrders();
    }

    public LiveData<List<Customers>> getAllCustomers() {
        return customerDao.getAllCustomers();
    }

    public LiveData<List<Employees>> getAllEmployees() {
        return employeeDao.getAllEmployees();
    }

    public LiveData<List<Items>> getAllItems() {
        return itemDao.getAllItems();
    }
}
