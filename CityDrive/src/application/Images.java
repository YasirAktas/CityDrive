package application;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Images{

	public ImageView getImage(String imageN) {
		//the switch statement takes an argument String(the name of the image)
		switch(imageN) {
		//in each case it creates an image object and ImageView object of it, and returns the imageView object
		case "car":
			Image car=new Image("car.png");
			ImageView carv=new ImageView(car);
			carv.setFitWidth(55.0);
			carv.setFitHeight(55.0);
			return carv;
		case "minibus":
			Image minibus=new Image("bus.png");
			ImageView minibusv=new ImageView(minibus);
			setHW(minibusv);
			minibusv.toFront();
			return minibusv;
		case "bus":
			Image bus=new Image("bus (1).png");
			ImageView busv=new ImageView(bus);
			setHW(busv);
			return busv;
		case "Istanbul":
			Image istanbul=new Image("istanbul.jpeg");
			ImageView istv=new ImageView(istanbul);
			setHW(istv);
			istv.toBack();
			return istv;
		case "Mersin":
			Image mersin=new Image("mersin.jpeg");
			ImageView mv=new ImageView(mersin);
			setHW(mv);
			mv.toBack();
			return mv;
		case "Van":
			Image van=new Image("van.JPG");
			ImageView vanv=new ImageView(van);
			setHW(vanv);
			vanv.toBack();
			return vanv;
		case "Ankara":
			Image ankara=new Image("ankara.jpeg");
			ImageView ankarav=new ImageView(ankara);
			setHW(ankarav);
			ankarav.toBack();
			return ankarav;
		case "Izmir":
			Image izmir=new Image("izmir.jpeg");
			ImageView izmirv=new ImageView(izmir);
			setHW(izmirv);
			izmirv.toBack();
			return izmirv;
		case "Antalya":
			Image antalya=new Image("antalya.jpeg");
			ImageView antalyav=new ImageView(antalya);
			setHW(antalyav);
			antalyav.toBack();
			return antalyav;
		case "Konya":
			Image konya=new Image("bursa.JPG");
			ImageView konyav=new ImageView(konya);
			setHW(konyav);
			konyav.toBack();
			return konyav;
		case "Mus":
			Image mus=new Image("mus.JPG");
			ImageView musv=new ImageView(mus);
			setHW(musv);
			musv.toBack();
			return musv;
		case "Bursa":
			Image bursa=new Image("bursa.JPG");
			ImageView bursav=new ImageView(bursa);
			setHW(bursav);
			bursav.toBack();
			return bursav;
		case "Tekirdag":
			Image tek=new Image("istanbul.jpeg");
			ImageView tekv=new ImageView(tek);
			setHW(tekv);
			tekv.toBack();
			return tekv;
		case "Mugla":
			Image mug=new Image("mugla.JPG");
			ImageView mugv=new ImageView(mug);
			setHW(mugv);
			mugv.toBack();
			return mugv;
		case "Adana":
			Image adana=new Image("adana.JPG");
			ImageView adanav=new ImageView(adana);
			setHW(adanav);
			adanav.toBack();
			return adanav;
		case "Erzincan":
			Image erz=new Image("bodrum.PNG");
			ImageView erzv=new ImageView(erz);
			setHW(erzv);
			erzv.toBack();
			return erzv;
		case "Aydin":
			Image ayd=new Image("aydın.jpeg");
			ImageView aydv=new ImageView(ayd);
			setHW(aydv);
			aydv.toBack();
			return aydv;
		case "Rize":
			Image rize=new Image("rize.jpeg");
			ImageView rizev=new ImageView(rize);
			setHW(rizev);
			rizev.toBack();
			return rizev;
		case "Elazig":
			Image el=new Image("rize.jpeg");
			ImageView elv=new ImageView(el);
			setHW(elv);
			elv.toBack();
			return elv;
		case "Ordu":
			Image or=new Image("rize.jpeg");
			ImageView orv=new ImageView(or);
			setHW(orv);
			orv.toBack();
			return orv;
		case "Usak":
			Image us=new Image("rize.jpeg");
			ImageView usv=new ImageView(us);
			setHW(usv);
			usv.toBack();
			return usv;
		case "Bolu":
			Image bolu=new Image("rize.jpeg");
			ImageView boluv=new ImageView(bolu);
			setHW(boluv);
			boluv.toBack();
			return boluv;
		case "Kars":
			Image kar=new Image("rize.jpeg");
			ImageView karv=new ImageView(kar);
			setHW(karv);
			karv.toBack();
			return karv;
		case "Manisa":
			Image man=new Image("rize.jpeg");
			ImageView manv=new ImageView(man);
			setHW(manv);
			manv.toBack();
			return manv;
	
		case "fixed":
			Image fixed=new Image("pngwing.com (8).png");
			ImageView fixedv=new ImageView(fixed);
			setHW(fixedv);
			fixedv.toBack();
			return fixedv;
			
			
		}
		return null;
	
	}
	
	//this method sets the size of the image
		public void setHW(ImageView imageView) {
		imageView.setFitWidth(55.0);
		imageView.setFitHeight(55.0);
	}


	
}