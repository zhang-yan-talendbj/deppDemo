<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html><head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TCS SYSTEM</title>
<link rel="stylesheet" href="TCS%20SYSTEM_files/tcs.css" type="text/css">

<script language="javascript">
	  function setfocus()
	  {
		 document.Form_Main.staff_id.focus();
	  }
		function trim(str)
      {
         for(var i = 0 ; i<str.length && str.charAt(i)==" " ; i++ ) ;
         for(var j =str.length; j>0 && str.charAt(j-1)==" " ; j--) ;
         if(i>j) return "";
         return str.substring(i,j);
       }

		function Check_In()
		 {
		    var sTemp = document.Form_Main.staff_id.value;
            sTemp = trim(sTemp);
		   // alert(sTemp + "1");
			if(sTemp == "")
			{
				alert("Please input Staff ID");
				//document.Form_Main.staff_id.foucs();
				return false;
			}
			 document.Form_Main.action = "checkIn.jsp";
			return true;
		 }

		function Check_Out()
		 {
		    var sTemp = document.Form_Main.staff_id.value;
            sTemp = trim(sTemp);
			if(sTemp == "")
			{
				alert("Please input Staff ID");
	            //document.Form_Main.staff_id.foucs();
				return false;
			}
			 document.Form_Main.action = "checkOut.jsp";
			return true;
		 }

		function Inquiry()
		 {
		    var sTemp = document.Form_Main.staff_id.value;
            sTemp = trim(sTemp);
			if(sTemp == "")
			{
			   alert("Please input Staff ID");
			   return false;
			}
			  document.Form_Main.action = "PerInquiry";
			return true;
		 }
</script>
<script>try {  for(var lastpass_iter=0; lastpass_iter < document.forms.length; lastpass_iter++){    var lastpass_f = document.forms[lastpass_iter];    if(typeof(lastpass_f.lpsubmitorig)=="undefined"){      if (typeof(lastpass_f.submit) == "function") {        lastpass_f.lpsubmitorig = lastpass_f.submit;        lastpass_f.submit = function(){          var form = this;          try {            if (document.documentElement && 'createEvent' in document)            {              var forms = document.getElementsByTagName('form');              for (var i=0 ; i<forms.length ; ++i)                if (forms[i]==form)                {                  var element = document.createElement('lpformsubmitdataelement');                  element.setAttribute('formnum',i);                  element.setAttribute('from','submithook');                  document.documentElement.appendChild(element);                  var evt = document.createEvent('Events');                  evt.initEvent('lpformsubmit',true,false);                  element.dispatchEvent(evt);                  break;                }            }          } catch (e) {}          try {            form.lpsubmitorig();          } catch (e) {}        }      }    }  }} catch (e) {}</script></head>
<body div="" class="tcs_index_body_bg" leftmargin="0" topmargin="0" scroll="auto" onload="setfocus()" text="#000000">
<div align="center">
<form name="Form_Main" action="" method="post" align="center"><input id="password" name="password" type="hidden">
<table class="standardTable" id="borderTable" div="" height="100%" border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
  <tr height="55"><!--Update height from 20 to 120 in 20090113-->
    <td width="33%">&nbsp;</td>
    <td width="33%">&nbsp;</td>
    <td width="34%">&nbsp;</td>
  </tr>
  <tr>
    <td width="33%">&nbsp;</td>
    <td valign="top" width="33%">
      <table div="" class="tcs_index_title_bg" border="0" cellpadding="0" cellspacing="0" width="100%">
        <tbody>
        <tr><td height="53" align="center" valign="center">
        <span class="tcs_index_title"> TCS System </span>
        </td>
        </tr></tbody>
      </table>
      <table class="mainTable" id="mainTable" bgcolor="#eeeeee" border="0" cellpadding="0" cellspacing="0" width="503">
        <tbody>
        <tr>
          <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
          <td valign="top" width="100%">
            <table id="logoTable" dir="ltr" border="0" cellpadding="0" cellspacing="0" width="98%">
              <tbody>
              <tr>
                <td width="100%">
                  <table id="usertxtTable" border="0" cellpadding="0" cellspacing="0" width="100%">
                    <tbody>
                    <tr>
                      <td height="8" nowrap="nowrap" align="left" valign="top">&nbsp;</td>
                      <td width="10">&nbsp;</td>
                    </tr>
                    <tr>
                      <td height="8" nowrap="nowrap" align="left" valign="top">&nbsp;</td>
                      <td rowspan="2" width="10">&nbsp;</td>
                    </tr>
                    <tr>
                      <td valign="top">
                        <table id="contentTable" height="100%" border="0" cellpadding="0" cellspacing="0" width="100%">
                          <tbody>
                          <tr>
                            <td nowrap="nowrap" width="1%">
                              <p><label for="username"><span class="tcs_index_staff_id">Staff
                            ID:</span></label></p></td>
                            <td rowspan="7">&nbsp;&nbsp;</td>
                            <td colspan="2" width="100%">
							  <input maxlength="15" size="45" name="staff_id" type="text"><!--Update it in 20090212 size=45 -->
                              <input id="SubmitCreds" value="Inquiry" name="Submit" style="font-size: 11px;" onclick="return Inquiry()" div="" class="tcs_index_push_button" type="submit">&nbsp;&nbsp;&nbsp;
                            </td>
                            <td rowspan="4" width="16">&nbsp;&nbsp;</td>
                            <!--Delete it in 20090113<TD width="1%">&nbsp;</TD>-->
                          </tr>
                          <tr>
                            <td height="5"></td>
                            <td colspan="2" height="5" valign="top">&nbsp;</td>
                            <td height="5"></td>
                          </tr>
                          <tr>
                            <td height="12"></td>
                            <td colspan="2" height="12" valign="top">&nbsp;</td>
                            <td height="12"></td>
                          </tr>
                          <tr>
                            <td>&nbsp;</td>
                            <td>
                              <table border="0" cellpadding="0" cellspacing="0">
                                <tbody>
                                <tr>
                                <td colspan="2" width="100%"><!-- Remove align="center" this line and align="middle" the next line-->
                                 &nbsp;
								<input value="Check In" name="actionType" onclick="return Check_In()" div="" class="tcs_index_push_button" type="submit">
                                </td>
                                </tr>
                                </tbody>
                              </table>
                            </td>
                            <td>
                              <table border="0" cellpadding="0" cellspacing="0">
                                <tbody>
                                <tr>
                                <td colspan="2" width="100%">
                                <input value="Check Out" name="actionType" onclick="return Check_Out()" div="" class="tcs_index_push_button" type="submit">
                                </td>
                                </tr>
                                </tbody>
                              </table>
                            </td>
                          </tr>
                          <tr>
                            <td height="25"></td><!--Update height 10 to 25 .-->
                          </tr>
                          </tbody>
                        </table>
                      </td>
                    </tr>
                    </tbody>
                  </table>
                </td>
              </tr>
              <tr>
                <td colspan="1" width="100%">
                    <p><font color="#003366"> <br>
                      Click Button <b>"Check In"</b> to Check In; <br>
                      Click Button <b>"Check Out"</b> to Check Out;<br>
                      Click Button <b>"Inquiry"</b> to Inquiry Record;
                      </font></p>


                  <div class="tcs_index_PMA">Note:<a href="http://cibp3r8cweb01/pma">Do NOT forget to fulfill PMA
                  before you checkout.</a></div>

                  <br>
                  <div class="tcs_index_dsp_current_time" align="center">Current server time: May 2, 2012 4:14:48 PM</div></td>
              </tr>
              <tr>
                <td colspan="2" width="100%">&nbsp;</td>
              </tr>
              </tbody>
             </table>
          </td>
        </tr>
        </tbody>
      </table>
      <table div="" class="tcs_index_Address_bg" height="54" border="0" width="503"><!-- update width 566 to 400 in 20090114-->
           <tbody><tr>

             <td height="50" align="center" width="148"><img src="TCS%20SYSTEM_files/AIAIT.gif" height="37" border="0" width="103"></td>

             <td height="50" width="345">
               <table 100%"="" border="0 width=" width="335">
                 <tbody><tr>
                   <td height="18" align="right" width="329"><p><span class="tcs_index_dsp_Address">©AIA Information Technology(Beijing) CO., Ltd.</span></p></td>
                 </tr>
                 <tr>
                   <td align="right" width="329"><p class="MsoNormal"><span style="mso-fareast-language:ZH-CN"></span>
                   	<span class="tcs_index_dsp_Address">&#21451;&#37030;&#21019;&#26032;&#36164;&#35759;&#31185;&#25216;
               (&#21271;&#20140;)&#26377;&#38480;&#20844;&#21496;<o:p></o:p></span><span class="tcs_index_dsp_Address">.All rights reserved.</span>
                   </p></td>
                 </tr>
               </tbody></table>
              </td>
           </tr>
      </tbody></table>
    </td>
    <td width="34%">&nbsp;</td>
  </tr>
  <tr>
    <td width="33%">&nbsp;</td>
    <td width="33%">&nbsp;</td>
    <td width="34%">&nbsp;</td>
  </tr>
  </tbody>
</table>
</form>

</div></body></html>