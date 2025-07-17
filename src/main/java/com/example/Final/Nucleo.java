package com.example.Final;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Nucleo implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Nucleo.class, args);
	}

	@Override
	public void run(String... args) {
		mostrarBannerDeInicio();
	}

	private void mostrarBannerDeInicio() {
		System.out.println();
		System.out.println("╔═══════════════════════════════════════╗");
		System.out.println("║           INICIANDO E-COMMERCE        ║");
		System.out.println("╚═══════════════════════════════════════╝");
		System.out.println();
	}
}
