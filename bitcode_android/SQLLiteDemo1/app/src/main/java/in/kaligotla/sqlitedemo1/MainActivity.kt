package `in`.kaligotla.sqlitedemo1

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import `in`.kaligotla.sqlitedemo1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var dbUtil: DBUtil
    private lateinit var employees: ArrayList<Employee>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dbUtil = DBUtil(this)
        dbOperations()
    }

    private fun dbOperations() {
        employees = ArrayList()
        for (i in 0..10) {
            employees.add(Employee(i + 100, "Name $i", i * 5000 + 1000))
        }

        val inserted = dbUtil.insertEmployee(employees)
        if (inserted) Toast.makeText(this, "Inserted into DB", Toast.LENGTH_SHORT).show()

        val updateEmployee = employees[1]
        updateEmployee.empSalary = 123123
        dbUtil.updateEmployee(updateEmployee)

        dbUtil.deleteEmployee(employees[9])

        val all = dbUtil.getAllEmployees()
        binding.txtView.text = "All Employees:\n"
        for (emp in all) {
            binding.txtView.append("ID: ${emp.empId}, Name: ${emp.empName}, Salary: ${emp.empSalary}\n")
        }

        val highEarners = dbUtil.getHighEarningEmployees()
        binding.txtView.append("\nHigh Earners (>30000):\n")
        for (emp in highEarners) {
            binding.txtView.append("ID: ${emp.empId}, Name: ${emp.empName}, Salary: ${emp.empSalary}\n")
        }

        val grouped = dbUtil.getEmployeeCountGroupedBySalary()
        binding.txtView.append("\nGrouped by Salary:\n")
        for ((salary, count) in grouped) {
            binding.txtView.append("Salary: $salary, Count: $count\n")
        }

        val having = dbUtil.getGroupedHavingEmployees()
        binding.txtView.append("\nGrouped Having Count > 1:\n")
        for ((salary, count) in having) {
            binding.txtView.append("Salary: $salary, Count: $count\n")
        }

        val searchResult = dbUtil.searchEmployeeByIdOrName("102")
        binding.txtView.append("\nSearch Results for '102':\n")
        for (emp in searchResult) {
            binding.txtView.append("ID: ${emp.empId}, Name: ${emp.empName}, Salary: ${emp.empSalary}\n")
        }
    }
}