package com.kng.brtrprts.brtcontroller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.kng.brtrprts.brtformbean.BrtFormBean;
import com.kng.brtrprts.brtformbean.MedasReportBean;


@Controller
public class BirtGenController 
{
	
	public String sdate = "", edate = "",reportUrl="",k_nk_value="", u_value="",s_value="",k_mo_value=""; 
	public Integer oid,kwtnkwtid,usrtypeid,statusid,kngMohId ;
	
	// ---------------------------------------------------------------
	@Value("${birt.report.base.url}")
	private String reportBaseUrl;
	
	@Value("${birt.kuwaiti}")
	private String kwt;
	@Value("${birt.nonkuwaiti}")
	private String nkwt;
	@Value("${birt.admin}")
	private String admin;
	@Value("${birt.doctor}")
	private String doctor;
	@Value("${birt.nurse}")
	private String nurse;
	@Value("${birt.other}")
	private String other;
	@Value("${birt.rd}")
	private String rd;
	@Value("${birt.rg}")
	private String rg;
	@Value("${birt.active}")
	private String active;
	@Value("${birt.inactive}")
	private String inactive;
	@Value("${birt.kng}")
	private String kng;
	@Value("${birt.moh}")
	private String moh;
	// ---------------------------------------------------------------
	
	
	// ----------------------------------------------- Emergency Service CONTROLLER START ----------------------------------------------
	
	@GetMapping("/emergencyServiceReport")
	public void getsickLeaveBySumaryReport(HttpServletResponse response) throws IOException
	{
		System.out.println("Emergency Sevrice Report====" + reportBaseUrl);
	
		
				reportUrl= reportBaseUrl+"amb_srv.rptdesign";
		
			
				
		response.sendRedirect(reportUrl);
		
	}
	
	// ----------------------------------------------- Emergency Service CONTROLLER END ----------------------------------------------
	
	
	//-------------------------------------------------- Medas Users Controller START --------------------------------------------------
	
	@GetMapping("/medasUserListReport")
	public void getmedasUserListReport(@ModelAttribute BrtFormBean brtFormBean, HttpServletResponse response) throws IOException
	{
		System.out.println("Medas User List Report====" + reportBaseUrl);
		
		kwtnkwtid = brtFormBean.getBrtKwtNkwtid();
		usrtypeid = brtFormBean.getBrtUserType();
		statusid = brtFormBean.getBrtStatus();
		kngMohId = brtFormBean.getBrtKngMoh();
		
		System.out.println("Kuwaiti_Non-Kuwait_ID: "+kwtnkwtid+"\nUserType_ID: "+usrtypeid+"\nKNG_MOH_ID: "+kngMohId+"\nStatus_ID: "+statusid);
		
		 // Determine report name based on single-All logic
	    String reportName;
	    // ----------------------------------------------------------------------------
	    // When 4 of the filters -> All
	    if(statusid == 1 && kwtnkwtid == 1 && kngMohId == 1 && usrtypeid == 1)
	    {
	    	 reportName = "KngMsd_MedasUsers_All.rptdesign";
	    }
	    // ----------------------------------------------------------------------------
	    // When 1 of the filters -> All
	    else if (statusid == 1 && kwtnkwtid != 1 && kngMohId != 1 && usrtypeid != 1)
	    {
	        reportName = "KngMsd_MedasUsers_StatusAll.rptdesign";
	    } 
	    else if (usrtypeid == 1 && kwtnkwtid != 1 && kngMohId != 1 && statusid != 1) 
	    {
	        reportName = "KngMsd_MedasUsers_TypeAll.rptdesign";
	    } 
	    else if (kwtnkwtid == 1 && kngMohId != 1 && usrtypeid != 1 && statusid != 1)
	    {
	        reportName = "KngMsd_MedasUsers_KwtNkwtAll.rptdesign";
	    } 
	    else if (kngMohId == 1 && kwtnkwtid != 1 && usrtypeid != 1 && statusid != 1) 
	    {
	        reportName = "KngMsd_MedasUsers_KngMohAll.rptdesign";
	    } 
	    // ----------------------------------------------------------------------------
	    // When 2 of the filters -> All
	    else if(statusid == 1 && kwtnkwtid == 1 && kngMohId != 1 && usrtypeid != 1)
	    {
	    	 reportName = "KngMsd_MedasUsers_sk.rptdesign";
	    }
	    else if(statusid != 1 && kwtnkwtid == 1 && kngMohId == 1 && usrtypeid != 1)
	    {
	    	 reportName = "KngMsd_MedasUsers_km.rptdesign";
	    }
	    else if(statusid != 1 && kwtnkwtid != 1 && kngMohId == 1 && usrtypeid == 1)
	    {
	    	 reportName = "KngMsd_MedasUsers_mu.rptdesign";
	    }
	    else if(statusid == 1 && kwtnkwtid != 1 && kngMohId != 1 && usrtypeid == 1)
	    {
	    	 reportName = "KngMsd_MedasUsers_su.rptdesign";
	    }
	    // ----------------------------------------------------------------------------
	    // When 3 of the filters -> All
	    else if(statusid == 1 && kwtnkwtid == 1 && kngMohId == 1 && usrtypeid != 1)
	    {
	    	 reportName = "KngMsd_MedasUsers_skm.rptdesign";
	    }
	    else if(statusid != 1 && kwtnkwtid == 1 && kngMohId == 1 && usrtypeid == 1)
	    {
	    	 reportName = "KngMsd_MedasUsers_kmu.rptdesign";
	    }
	    else if(statusid == 1 && kwtnkwtid != 1 && kngMohId == 1 && usrtypeid == 1)
	    {
	    	 reportName = "KngMsd_MedasUsers_mus.rptdesign";
	    }
	    else if(statusid == 1 && kwtnkwtid == 1 && kngMohId != 1 && usrtypeid == 1)
	    {
	    	 reportName = "KngMsd_MedasUsers_sku.rptdesign";
	    }
	    // ----------------------------------------------------------------------------
	    // When SPECIFIC filters are applied
	    else
	    {
	        reportName = "KngMsd_MedasUsers_specific.rptdesign";
	    }
	    // ----------------------------------------------------------------------------
	    MedasReportBean mrb = getReportParams(kwtnkwtid,usrtypeid,kngMohId,statusid);

	    // Build the report URL with applicable query params
	    StringBuilder reportUrlBuilder = new StringBuilder(reportBaseUrl).append(reportName);

	    // ----------------------------------------------------------------------------
	    // Parameters add here
	    if (kwtnkwtid != 1) {
	        reportUrlBuilder.append("&KwtNkwt=").append(mrb.getKNkValue());
	    }
	    if (kngMohId != 1) {
	        reportUrlBuilder.append("&KngMoh=").append(mrb.getKMoValue());
	    }
	    if (usrtypeid != 1) {
	        reportUrlBuilder.append("&Type=").append(mrb.getUValue());
	    }
	    if (statusid != 1) {
	        reportUrlBuilder.append("&Status=").append(mrb.getSValue());
	    }
	    // ----------------------------------------------------------------------------
	    //reportUrlBuilder.toString();
				
		response.sendRedirect(reportUrlBuilder.toString());
		
	}
	
	//-------------------------------------------------- Medas Users Controller START --------------------------------------------------

	
	
	public MedasReportBean getReportParams(int kwtnkwtid, int usrtypeid, int kngMohId,int statusid) 
	{
		System.out.println("getReportParams Method:====> "+kwtnkwtid+" "+usrtypeid+" "+kngMohId+" "+statusid);
		k_nk_value = (kwtnkwtid == 2) ? kwt : nkwt;
		k_mo_value = (kngMohId == 2) ? kng : moh;
		s_value   = (statusid == 2) ? active : inactive;
		u_value   = (usrtypeid == 2) ? admin :
					(usrtypeid == 3) ? doctor :
					(usrtypeid == 4) ? nurse :
					(usrtypeid == 5) ? other :
					(usrtypeid == 6) ? rd :
						  (usrtypeid == 7) ? rg : "";
		
		return new MedasReportBean(k_nk_value, k_mo_value, s_value, u_value);
	}
	
	
	
}
