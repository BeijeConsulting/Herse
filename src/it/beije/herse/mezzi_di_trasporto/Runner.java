package it.beije.herse.mezzi_di_trasporto;

import it.beije.herse.mezzi_di_trasporto.mezzi.aereo.Aereo;
import it.beije.herse.mezzi_di_trasporto.mezzi.aereo.AereoBenzina;
import it.beije.herse.mezzi_di_trasporto.mezzi.auto.Auto;
import it.beije.herse.mezzi_di_trasporto.mezzi.auto.AutoBenzina;
import it.beije.herse.mezzi_di_trasporto.mezzi.auto.AutoElettrica;
import it.beije.herse.mezzi_di_trasporto.mezzi.bici.Bici;
import it.beije.herse.mezzi_di_trasporto.mezzi.bici.BiciPedali;
import it.beije.herse.mezzi_di_trasporto.mezzi.navi.Nave;
import it.beije.herse.mezzi_di_trasporto.mezzi.navi.NaveDiesel;
import it.beije.herse.mezzi_di_trasporto.mezzi.treno.Treno;
import it.beije.herse.mezzi_di_trasporto.mezzi.treno.TrenoElettrico;

public class Runner {

	public static void main(String[] args) {
		Auto auto = new AutoBenzina();
		auto.numeroPosti(5);
		auto.numeroRuote(4);
		auto.setCambio('M');
		auto.sottotipo("Utilitaria");
		auto.stampaTipoAuto();
		
		auto = new AutoElettrica();
		auto.stampaTipoAuto();
		
		System.out.println("\n");
		
		Aereo aereo = new AereoBenzina();
		aereo.stampaTipoAereo();
		aereo.proprietà("Privato");
		aereo.tipoTratta("Intercontinentale");
		
		System.out.println("\n");
		
		Nave nave = new NaveDiesel();
		nave.numeroPosti(30);
		nave.stampaTipoNave();
		
		System.out.println("\n");
		
		Treno treno = new TrenoElettrico();
		treno.stampaTipoTreno();
		treno.numeroVagoni(5);
		treno.tipoTratta("Regionale");
		
		System.out.println("\n");
		
		Bici bici = new BiciPedali();
		bici.numeroPosti(1);
		bici.numeroRuote(2);
		bici.stampaTipoBici();
	}

}
