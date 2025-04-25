package com.mymakeupdatabase.backend;

import com.mymakeupdatabase.backend.entities.MakeupProduct;
import com.mymakeupdatabase.backend.repositories.MakeupProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	CommandLineRunner run(MakeupProductRepository makeupProductRepository) {
		makeupProductRepository.deleteAll();

		return args -> {
			MakeupProduct luvCheekBalm = new MakeupProduct();
			luvCheekBalm.setName("Luv Beam Cheek Balm");
			luvCheekBalm.setType("Blush");
			luvCheekBalm.setBrand("lilybyred");
			luvCheekBalm.setPrice(new BigDecimal("15.35"));
			luvCheekBalm.setPriceRange("Drugstore");
			luvCheekBalm.setCountry("South Korea");

			MakeupProduct wateryEssence = new MakeupProduct();
			wateryEssence.setName("Watery Essence Lip Gloss");
			wateryEssence.setType("Lip Gloss");
			wateryEssence.setBrand("Judydoll");
			wateryEssence.setPrice(new BigDecimal("16.54"));
			wateryEssence.setPriceRange("Drugstore");
			wateryEssence.setCountry("China");

			MakeupProduct coverPerfection = new MakeupProduct();
			coverPerfection.setName("Cover Perfection Triple Pot Concealer");
			coverPerfection.setType("Concealer");
			coverPerfection.setBrand("The Saem");
			coverPerfection.setPrice(new BigDecimal("14.16"));
			coverPerfection.setPriceRange("Drugstore");
			coverPerfection.setCountry("South Korea");

			MakeupProduct reformingContourStick = new MakeupProduct();
			reformingContourStick.setName("Re-Forming Contour Stick");
			reformingContourStick.setType("Contour");
			reformingContourStick.setBrand("colorgram");
			reformingContourStick.setPrice(new BigDecimal("12.52"));
			reformingContourStick.setPriceRange("Drugstore");
			reformingContourStick.setCountry("South Korea");

			MakeupProduct kissMeHeroineLiquidEyeliner = new MakeupProduct();
			kissMeHeroineLiquidEyeliner.setName("Kiss Me Heroine Make Smooth Liquid Eyeliner");
			kissMeHeroineLiquidEyeliner.setType("Eyeliner");
			kissMeHeroineLiquidEyeliner.setBrand("ISEHAN");
			kissMeHeroineLiquidEyeliner.setPrice(new BigDecimal("15.50"));
			kissMeHeroineLiquidEyeliner.setPriceRange("Drugstore");
			kissMeHeroineLiquidEyeliner.setCountry("Japan");

			MakeupProduct curlingEyelashIronMascara = new MakeupProduct();
			curlingEyelashIronMascara.setName("3D Curling Eyelash Iron Mascara");
			curlingEyelashIronMascara.setType("Mascara");
			curlingEyelashIronMascara.setBrand("Judydoll");
			curlingEyelashIronMascara.setPrice(new BigDecimal("14.45"));
			curlingEyelashIronMascara.setPriceRange("Drugstore");
			curlingEyelashIronMascara.setCountry("China");

			MakeupProduct maskFitRedCushion = new MakeupProduct();
			maskFitRedCushion.setName("Mask Fit Red Cushion");
			maskFitRedCushion.setType("Foundation");
			maskFitRedCushion.setBrand("TIRTIR");
			maskFitRedCushion.setPrice(new BigDecimal("37.25"));
			maskFitRedCushion.setPriceRange("Drugstore");
			maskFitRedCushion.setCountry("South Korea");

			MakeupProduct pinPointEyeshadowPalette = new MakeupProduct();
			pinPointEyeshadowPalette.setName("Pin Point Eyeshadow Palette");
			pinPointEyeshadowPalette.setType("Eye Shadow");
			pinPointEyeshadowPalette.setBrand("colorgram");
			pinPointEyeshadowPalette.setPrice(new BigDecimal("25.18"));
			pinPointEyeshadowPalette.setPriceRange("Drugstore");
			pinPointEyeshadowPalette.setCountry("South Korea");

			MakeupProduct juicyLastingTintBareJuicy = new MakeupProduct();
			juicyLastingTintBareJuicy.setName("Juicy Lasting Tint Bare Juicy Series");
			juicyLastingTintBareJuicy.setType("Lip Tint");
			juicyLastingTintBareJuicy.setBrand("romand");
			juicyLastingTintBareJuicy.setPrice(new BigDecimal("17.88"));
			juicyLastingTintBareJuicy.setPriceRange("Drugstore");
			juicyLastingTintBareJuicy.setCountry("South Korea");

			MakeupProduct inkMoodGlowyTint = new MakeupProduct();
			inkMoodGlowyTint.setName("Ink Mood Glowy Tint");
			inkMoodGlowyTint.setType("Lip Tint");
			inkMoodGlowyTint.setBrand("peripera");
			inkMoodGlowyTint.setPrice(new BigDecimal("11.31"));
			inkMoodGlowyTint.setPriceRange("Drugstore");
			inkMoodGlowyTint.setCountry("South Korea");

			MakeupProduct blendingMoodCheekIceCream = new MakeupProduct();
			blendingMoodCheekIceCream.setName("Blending Mood Cheek Ice Cream Edition");
			blendingMoodCheekIceCream.setType("Blush");
			blendingMoodCheekIceCream.setBrand("dasique");
			blendingMoodCheekIceCream.setPrice(new BigDecimal("25.03"));
			blendingMoodCheekIceCream.setPriceRange("Drugstore");
			blendingMoodCheekIceCream.setCountry("South Korea");

			makeupProductRepository.save(luvCheekBalm);
			makeupProductRepository.save(wateryEssence);
			makeupProductRepository.save(coverPerfection);
			makeupProductRepository.save(reformingContourStick);
			makeupProductRepository.save(kissMeHeroineLiquidEyeliner);
			makeupProductRepository.save(curlingEyelashIronMascara);
			makeupProductRepository.save(maskFitRedCushion);
			makeupProductRepository.save(pinPointEyeshadowPalette);
			makeupProductRepository.save(juicyLastingTintBareJuicy);
			makeupProductRepository.save(inkMoodGlowyTint);
			makeupProductRepository.save(blendingMoodCheekIceCream);
		};
	}
}
