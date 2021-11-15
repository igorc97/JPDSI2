package com.jsfcourse.calc;

import javax.inject.Inject;
import javax.inject.Named;

import java.text.NumberFormat;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named
@RequestScoped
//@SessionScoped
public class CalcBB {
	private Double kwota;
	private Double lata;
	private Double procent;
	private Double result;
	private Double secres;

	@Inject
	FacesContext ctx;

	

	public Double getKwota() {
		return kwota;
	}

	public void setKwota(Double kwota) {
		this.kwota = kwota;
	}

	public Double getLata() {
		return lata;
	}

	public void setLata(Double lata) {
		this.lata = lata;
	}

	public Double getProcent() {
		return procent;
	}

	public void setProcent(Double procent) {
		this.procent = procent;
	}

	public Double getResult() {
		return result;
	}

	public void setResult(Double result) {
		this.result = result;
	}
	
	public Double getSecres() {
		return secres;
	}

	public void setSecres(Double secres) {
		this.secres = secres;
	}

	public boolean doTheMath() {
		try {
			double KwotaOproc = kwota * procent;
			result = (kwota/(lata * 12) + KwotaOproc); //kwota po wyliczeniach + kwota oprocentowania
			secres = (kwota/(lata * 12)); //kwota po wyliczeniach bez kwoty oprocentowania
			
			

			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacja wykonana poprawnie", null));
			return true;
		} catch (Exception e) {
			ctx.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd podczas przetwarzania parametrów", null));
			return false;
		}
	}

	// Go to "showresult" if ok
	public String calc() {
		if (doTheMath()) {
			return "showresult";
		}
		return null;
	}

	// Put result in messages on AJAX call
	public String calc_AJAX() {
		if (doTheMath()) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Wynik z kwotą oprocentowania: " + result, null));
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Wynik bez oprocentowania: " + secres, null));
		}
		return null;
	}

	public String info() {
		return "info";
	}
}
