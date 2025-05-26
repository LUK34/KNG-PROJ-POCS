package com.kng.brtrprts.brtcontroller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.kng.brtrprts.brtformbean.BrtFormBean;
import com.kng.brtrprts.brtservice.BirtReportService;

@Controller
public class BirtPhaController 
{
	Logger logger = LoggerFactory.getLogger(BirtPhaController.class);
	
	@Autowired
	public BrtFormBean brtFormBean;
	
	@Autowired
	public BirtReportService bs;
	
	public String sdate="", edate="",patientid="",militaryid="",civilid="",mrno="",reportUrl="",medy="",filler="",pharmItemid=""; 
	public Integer oid,demogid,transcid;
	public List<String> medid;

	
	@Value("${birt.report.base.url}")
	private String reportBaseUrl;
	
	
	
	@GetMapping("/medicineStockClinicWise")	
	public void getmedicineStockClinicWiseReport(@ModelAttribute BrtFormBean brtFormBean, HttpServletResponse response) throws IOException
	{
		System.out.println("medicineStockClinicWise====" + reportBaseUrl);
	
		
		oid	=	brtFormBean.getBrtOid();
		if(brtFormBean.getBrtOid()==0)
		{					
			reportUrl= reportBaseUrl+"KngMsd_PharamacyStockByAll.rptdesign";
			//reportUrl= reportBaseUrl+"KngMsd_PharamacyStockByAll_copy.rptdesign";
		}
		else 
		{
			reportUrl= reportBaseUrl+"KngMsd_PharamacyStockByClinic.rptdesign&OID="+oid;
			//reportUrl= reportBaseUrl+"KngMsd_PharamacyStockByClinic_copy.rptdesign&OID="+oid;
		}
		
		response.sendRedirect(reportUrl);
	}
	
	
	//phavisitOfficerSoldierCivilianFamily
	@GetMapping("/phavisitOfficerSoldierCivilianFamily")	
	public void getphavisitOfficerSoldierCivilianFamilyReport(@ModelAttribute BrtFormBean brtFormBean, HttpServletResponse response) throws IOException
	{
		  System.out.println("phavisitOfficerSoldierCivilianFamily====" + reportBaseUrl);
		
		  sdate	=brtFormBean.getBrtSdate();
		  edate	=brtFormBean.getBrtEdate();
		  
		  if(brtFormBean.getBrtSdate()!=null && brtFormBean.getBrtEdate()!=null)
		  {
			  reportUrl= reportBaseUrl+"civil_med_catgory.rptdesign&SDATE="+sdate+"&EDATE="+edate;
		  }
		  response.sendRedirect(reportUrl);
		
	}
	
	//pharmacyVisitHistory
	@GetMapping("/pharmacyVisitHistory")	
	public void getpharmacyVisitHistoryReport(@ModelAttribute BrtFormBean brtFormBean, HttpServletResponse response) throws IOException
	{
		System.out.println("pharmacyVisitHistory====" + reportBaseUrl);
		
		patientid=brtFormBean.getBrtPid();
		
		if(brtFormBean.getBrtPid()!=null)
		{
			if (patientid.length() < 10)
			{
				militaryid = patientid.trim();
				reportUrl= reportBaseUrl+"KngMsd_PharmacyVisitHistory_ByMilitaryNo.rptdesign&PID="+militaryid;
			}
				
			else if (patientid.length() == 12)
			{
				civilid = patientid.trim();
				reportUrl= reportBaseUrl+"KngMsd_PharmacyVisitHistory_ByCivilId.rptdesign&PID="+civilid;
			}
				
			else if (patientid.length()==13)
			{
				mrno = patientid.trim();
				reportUrl= reportBaseUrl+"KngMsd_PharmacyVisitHistory_ByMedicalRecordNo.rptdesign&PID="+mrno;
			}
		}
		response.sendRedirect(reportUrl);
	}
	
	
	//pharmacyItemsTotalDispensedReport
	@GetMapping("/pharmacyItemsTotalDispensedReport")	
	public void getpharmacyItemsTotalDispensedReport(@ModelAttribute BrtFormBean brtFormBean, HttpServletResponse response) throws IOException
	{
		System.out.println("pharmacyItemsTotalDispensedReport====" + reportBaseUrl);
		
			sdate=brtFormBean.getBrtSdate();
			edate=brtFormBean.getBrtEdate();
			oid=brtFormBean.getBrtOid();
			medid=brtFormBean.getBrtMedicineid();
			
			if(brtFormBean.getBrtSdate()!=null && brtFormBean.getBrtEdate()!=null)
			{	
				if(brtFormBean.getBrtOid()==0)// user selects all clinic scenario
				{
					reportUrl= reportBaseUrl+"KngMsd_////.rptdesign&SDATE="+sdate+"&EDATE="+edate;
				}
				else
				{
					
					if (medid != null && !medid.isEmpty()) 
					{
						 medy = medid.stream()
	                                .map(String::valueOf)
	                                .collect(Collectors.joining(","));
						 
						 reportUrl= reportBaseUrl+"KngMsd_PharmacyItemDispnse_ByOffice.rptdesign&SDATE="+sdate+"&EDATE="+edate+"&OID="+oid+"&MEDID="+medy;
					}
					
					/*
					if (medid != null && !medid.isEmpty()) {
					    medy = medid.stream()
					                .map(id -> "'" + id + "'") // Enclose each id in single quotes
					                .collect(Collectors.joining(",")); // Join them with a comma

					    reportUrl = reportBaseUrl + "KngMsd_PharmacyItemDispnse_ByOffice.rptdesign&SDATE=" + sdate + "&EDATE=" + edate + "&OID=" + oid + "&MEDID=" + medy;
					}*/
				}
			}
			response.sendRedirect(reportUrl);
		
	 }
	
	
	
	 //medicineByKngPharmacyReport
	@GetMapping("/medicineByKngPharmacyReport")	
	public void getmedicineByKngPharmacyReport(@ModelAttribute BrtFormBean brtFormBean, HttpServletResponse response) throws IOException
	{
		System.out.println("medicineByKngPharmacyReport====" + reportBaseUrl);
		
		sdate=brtFormBean.getBrtSdate();
		edate=brtFormBean.getBrtEdate();
		oid=brtFormBean.getBrtOid();
		demogid=brtFormBean.getBrtKwtNkwtid();
		militaryid=brtFormBean.getBrtPid();
		
				
		//brtFormBean.getBrtKwtNkwtid() {Kuwaiti = 1, Non-Kuwaiti = 2}
			if(brtFormBean.getBrtSdate()!=null && brtFormBean.getBrtEdate()!=null)
			{
				if(brtFormBean.getBrtOid()==0)// ALL CLINIC SCENARIO
				{
					if(demogid==1) //all clinic->Kuwaiti
					{
						if(militaryid !=null)//all clinic->Kuwaiti->military number
						{
							reportUrl= reportBaseUrl+"KngMsd_civil_medicines_All_kuw_Militaryid.rptdesign&SDATE="+sdate+"&EDATE="+edate+"&PID="+militaryid;
						}
						else //all clinic->Kuwaiti-> without military number
						{
							reportUrl= reportBaseUrl+"KngMsd_civil_medicines_All_kuw.rptdesign&SDATE="+sdate+"&EDATE="+edate;
						}
					}
					else	//all clinic->Non Kuwaiti
					{
						if(militaryid !=null)//all clinic->Non Kuwaiti->military number
						{
							reportUrl= reportBaseUrl+"KngMsd_civil_medicines_All_nonkuw_Militaryid.rptdesign&SDATE="+sdate+"&EDATE="+edate+"&PID="+militaryid;
						}
						else //all clinic->Non Kuwaiti-> without military number
						{
							reportUrl= reportBaseUrl+"KngMsd_civil_medicines_All_nonkuw.rptdesign&SDATE="+sdate+"&EDATE="+edate;
						}
					}
				}
				else // SPECIFIC CLINIC SCENARIO
				{
					if(demogid==1) //specific clinic->Kuwaiti
					{
						if(militaryid !=null)//specific clinic->Kuwaiti->military number
						{
							reportUrl= reportBaseUrl+"KngMsd_civil_medicines_Clinic_kuw_Militaryid.rptdesign&SDATE="+sdate+"&EDATE="+edate+"&OID="+oid+"&PID="+militaryid;
						}
						else //specific clinic->Kuwaiti-> without military number
						{
							reportUrl= reportBaseUrl+"KngMsd_civil_medicines_Clinic_kuw.rptdesign&SDATE="+sdate+"&EDATE="+edate+"&OID="+oid;
						}
					}
					else //specific clinic->Non-Kuwaiti
					{
						if(militaryid !=null)//specific clinic->Non-Kuwaiti->military number
						{
							reportUrl= reportBaseUrl+"KngMsd_civil_medicines_Clinic_nonkuw_Militaryid.rptdesign&SDATE="+sdate+"&EDATE="+edate+"&OID="+oid+"&PID="+militaryid;
						}
						else //specific clinic->Non-Kuwaiti-> without military number
						{
							reportUrl= reportBaseUrl+"KngMsd_civil_medicines_Clinic_nonkuw.rptdesign&SDATE="+sdate+"&EDATE="+edate+"&OID="+oid;
						}
						
					}
				}
			}
		
		
		response.sendRedirect(reportUrl);
	}
	
	
	@GetMapping("/phaTrackItemTransReport")	
	public void getphaTrackItemTransReport(@ModelAttribute BrtFormBean brtFormBean, HttpServletResponse response) throws IOException
	{
		System.out.println("phaTrackItemTransReport====" + reportBaseUrl);
		sdate=brtFormBean.getBrtSdate();
		edate=brtFormBean.getBrtEdate();
		oid=brtFormBean.getBrtOid();
		pharmItemid=brtFormBean.getBrtPharmItemId();
		transcid=brtFormBean.getBrtTranscTypeId(); //Material Request->1, Purchase->2, Sale->3
		System.out.println("Start Date: "+sdate+"End Date: "+edate+"Office Id: "+oid+"Pharmacy Item Id: "+pharmItemid+"Transaction Type Id: "+transcid);
		
		if(brtFormBean.getBrtSdate()!=null && brtFormBean.getBrtEdate()!=null)
		{
			if(brtFormBean.getBrtOid()!=0)// ALL CLINIC SCENARIO
			{
				if(transcid == 1)//Material Request
				{
					reportUrl= reportBaseUrl+"KngMsd_Pharmacy_TrackItem_Type.rptdesign&SDATE="+sdate+"&EDATE="+edate+"&OID="+oid+"&ITEMID="+pharmItemid+"&TYPE=MT";
				}
				if(transcid ==2)//Purchase
				{
					reportUrl= reportBaseUrl+"KngMsd_Pharmacy_TrackItem_Type.rptdesign&SDATE="+sdate+"&EDATE="+edate+"&OID="+oid+"&ITEMID="+pharmItemid+"&TYPE=P";
				}
				if(transcid ==3)//Sale
				{
					reportUrl= reportBaseUrl+"KngMsd_Pharmacy_TrackItem_Type.rptdesign&SDATE="+sdate+"&EDATE="+edate+"&OID="+oid+"&ITEMID="+pharmItemid+"&TYPE=S";
				}
			}
		}
		
		  response.sendRedirect(reportUrl);
	}
	
	@GetMapping("/phaMedicineReportTransReport")	
	public void getphaMedicineReportTransReport(@ModelAttribute BrtFormBean brtFormBean, HttpServletResponse response) throws IOException
	{
		System.out.println("phaMedicineReportTransReport====" + reportBaseUrl);
		sdate=brtFormBean.getBrtSdate();
		edate=brtFormBean.getBrtEdate();
		
		if(brtFormBean.getBrtSdate()!=null && brtFormBean.getBrtEdate()!=null)
		{
			  reportUrl= reportBaseUrl+"KngMsd_Pha_TrackItem_YearReport.rptdesign&SDATE="+sdate+"&EDATE="+edate;
		}
		  response.sendRedirect(reportUrl);
	}
	
	@GetMapping("/totalStockClinicWiseReport")
	public void gettotalStockClinicWiseReport(HttpServletResponse response) throws IOException
	{
		System.out.println("Total Stock Clinic Wise Report====" + reportBaseUrl);	
		
				reportUrl= reportBaseUrl+"KngMsd_Pha_TotalStock_CampWise.rptdesign";
				//reportUrl= reportBaseUrl+"KngMsd_Pha_TotalStock_CampWise_copy.rptdesign";

		response.sendRedirect(reportUrl);
		
	}
	
	@GetMapping("/eclinicPharmacyReport")
	public void getEclinicPharmacyReport(@ModelAttribute BrtFormBean brtFormBean, HttpServletResponse response) throws IOException
	{
		System.out.println("eclinicPharmacyReport====" + reportBaseUrl);
		sdate=brtFormBean.getBrtSdate();
		edate=brtFormBean.getBrtEdate();
		oid=brtFormBean.getBrtOid();
		
		if(brtFormBean.getBrtSdate()!=null && brtFormBean.getBrtEdate()!=null)
		{
			reportUrl=reportBaseUrl+"KngMsd_Pharmacy_ItemCurrentStock_Date.rptdesign&SDATE="+sdate+"&EDATE="+edate+"&OID="+oid;
		}
		
		response.sendRedirect(reportUrl);
	}
	
	
	
	
	
	

}
