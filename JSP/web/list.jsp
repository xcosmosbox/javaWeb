<!DOCTYPE html>
<html lang='en'>
<head>
    <meta charset='UTF-8'>
    <title>dept list</title>
</head>
<body>

  <script type='text/javascript'>
    function del(del_no) {
      var ok = window.confirm('Double check for deleting!')
      if(ok){
        document.location.href = 'oa_module/dept/delete?deptno=' + del_no;
      }
    }

  </script>


  <h1 align='center'>dept list</h1>
  <hr />
  <table border='1px' align='center' width='50%'>
    <tr>
      <th>serial_no</th>
      <th>dept_no</th>
      <th>dept_name</th>
      <th>operation</th>
    </tr>
    <tr>
      <td>1</td>
      <td>10</td>
      <td>sale</td>
      <td>
        <a href='javascript:void(0)' onclick='del(10)'>delete</a>
        <a href='edit.jsp'>modify</a>
        <a href='detail.jsp'>more info</a>
      </td>
    </tr>
  </table>
  <hr />
  <a href='add.jsp'>add new dept</a>
</body>
</html>