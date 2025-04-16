package `in`.kaligotla.sqlitedemo1

import android.content.ContentValues
import android.content.Context
import android.util.Log

class DBUtil(context: Context) {
    var dbHelper = DBHelper(context, "db_bitcode", null, 1)
    var dbBitcode = dbHelper.writableDatabase

    fun getAllEmployees(): ArrayList<Employee> {
        val employees = ArrayList<Employee>()
        val cursor = dbBitcode.query("Employee", null, null, null, null, null, "emp_id ASC")
        while (cursor.moveToNext()) {
            employees.add(Employee(cursor.getInt(0), cursor.getString(1), cursor.getInt(2)))
        }
        cursor.close()
        return employees
    }

    fun insertEmployee(employees: ArrayList<Employee>): Boolean {
        var rowId: Long = 0
        for (employee in employees) {
            val contentValues = ContentValues()
            contentValues.put("emp_id", employee.empId)
            contentValues.put("emp_name", employee.empName)
            contentValues.put("emp_salary", employee.empSalary)
            rowId = dbBitcode.insert("Employee", null, contentValues)
            Log.e("Tag", "$rowId")
        }
        return rowId.toInt() != -1
    }

    fun updateEmployee(employee: Employee): Int {
        val contentValues = ContentValues()
        contentValues.put("emp_id", employee.empId)
        contentValues.put("emp_name", employee.empName)
        contentValues.put("emp_salary", employee.empSalary)
        return dbBitcode.update("Employee", contentValues, "emp_id = ?", arrayOf(employee.empId.toString()))
    }

    fun deleteEmployee(employee: Employee): Int {
        return dbBitcode.delete("Employee", "emp_id=?", arrayOf(employee.empId.toString()))
    }

    fun getHighEarningEmployees(): ArrayList<Employee> {
        val employees = ArrayList<Employee>()
        val columns = arrayOf("emp_id", "emp_name", "emp_salary")
        val selection = "emp_salary > ?"
        val selectionArgs = arrayOf("30000")
        val cursor = dbBitcode.query(
            "Employee", columns, selection, selectionArgs,
            null, null, "emp_salary DESC"
        )

        while (cursor.moveToNext()) {
            employees.add(Employee(cursor.getInt(0), cursor.getString(1), cursor.getInt(2)))
        }
        cursor.close()
        return employees
    }

    fun getEmployeeCountGroupedBySalary(): Map<Int, Int> {
        val salaryCountMap = mutableMapOf<Int, Int>()
        val columns = arrayOf("emp_salary", "COUNT(emp_id) AS emp_count")
        val cursor = dbBitcode.query(
            "Employee", columns,
            null, null,
            "emp_salary",  // groupBy
            null,          // having
            "emp_salary ASC"
        )

        while (cursor.moveToNext()) {
            val salary = cursor.getInt(0)
            val count = cursor.getInt(1)
            salaryCountMap[salary] = count
        }
        cursor.close()
        return salaryCountMap
    }

    fun getGroupedHavingEmployees(): ArrayList<Pair<Int, Int>> {
        val result = ArrayList<Pair<Int, Int>>()
        val columns = arrayOf("emp_salary", "COUNT(emp_id) AS emp_count")
        val cursor = dbBitcode.query(
            "Employee", columns,
            null, null,
            "emp_salary",
            "COUNT(emp_id) > 1",
            "emp_salary DESC"
        )

        while (cursor.moveToNext()) {
            val salary = cursor.getInt(0)
            val count = cursor.getInt(1)
            result.add(Pair(salary, count))
        }
        cursor.close()
        return result
    }

    fun searchEmployeeByIdOrName(keyword: String): ArrayList<Employee> {
        val employees = ArrayList<Employee>()
        val columns = arrayOf("emp_id", "emp_name", "emp_salary")
        val selection = "emp_id = ? OR emp_name LIKE ?"
        val selectionArgs = arrayOf(keyword, "%$keyword%")

        val cursor = dbBitcode.query(
            "Employee", columns,
            selection, selectionArgs,
            null, null, "emp_name ASC"
        )

        while (cursor.moveToNext()) {
            employees.add(Employee(cursor.getInt(0), cursor.getString(1), cursor.getInt(2)))
        }
        cursor.close()
        return employees
    }
}