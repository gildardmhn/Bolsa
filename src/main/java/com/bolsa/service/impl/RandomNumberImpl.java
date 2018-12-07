package com.bolsa.service.impl;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.bolsa.service.RandomNumber;

@Service
public class RandomNumberImpl implements RandomNumber {

	@Override
	public float randomValorAcao() {
		Random random = new Random();
		return 10 + (1 * random.nextFloat());
	}
}
