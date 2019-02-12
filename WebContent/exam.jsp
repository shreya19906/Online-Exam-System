<%@ page import="java.util.*,com.Exam.*,com.Question.*" %>


<!DOCTYPE html>
<html>
<head>
<title>ONLINE EXAMINATION</title>
<style>
   
    #timer{
             position : fixed;
             left : 800px;
             color : red;
    
    }
    
    #myform{
             width : 750px;
             text-align : justify;
    }

</style>
</head>
<body bgcolor="#E6E6FA">


<h1 id="timer"></h1s>
  <script>
  document.getElementById('timer').innerHTML =
  10 + ":" + 00;
startTimer();

function startTimer() {
  var presentTime = document.getElementById('timer').innerHTML;
  var timeArray = presentTime.split(/[:]+/);
  var m = timeArray[0];
  var s = checkSecond((timeArray[1] - 1));
  if(s==59){m=m-1}
  if(m<0)
  {
	  alert('Time over');
	  document.myform.submit();
  }
  
  document.getElementById('timer').innerHTML =
    m + ":" + s;
  setTimeout(startTimer, 1000);
}

function checkSecond(sec) {
  if (sec < 10 && sec >= 0) {sec = "0" + sec}; // add zero in front of numbers < 10
  if (sec < 0) {sec = "59"};
  return sec;
}
</script>



<h1>DIG.COM</h1>

<h2>Welcome</h2>

<% List<Question> theQuestions=(List<Question>) session.getAttribute("QUESTION_LIST");
%>

<form action="ExamSystemServlet" method="GET"  id="myform">
  <input type="hidden" name="command" value="exam"/>
<%
 int i=0;
  for(Question q:theQuestions){ 
    i++;
    String s="ans"+i;
    %>
 

  <%=i+"." %> <%=q.getQues() %><br><br>
  <input type="radio" name=<%=s%> value="<%=q.getA()%>"><%=q.getA()%><br>
  <input type="radio" name=<%=s%> value="<%=q.getB()%>"><%=q.getB()%><br>
  <input type="radio" name=<%=s%> value="<%=q.getC()%>"><%=q.getC()%><br>
  <input type="radio" name=<%=s%> value="<%=q.getD()%>"><%=q.getD()%><br><br><br>
 
  <%} %>
  <input type="Submit" value="submit"/>

</form>





</body>
</html>