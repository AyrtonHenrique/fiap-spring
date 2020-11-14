package com.fiap.sts.stsfiap.controllers;

import com.fiap.sts.stsfiap.JwtTokenUtil;
import com.fiap.sts.stsfiap.models.Usuario;
import com.fiap.sts.stsfiap.repositories.UsuariosRepository;
import com.fiap.sts.stsfiap.viewModels.JwtRequest;
import com.fiap.sts.stsfiap.viewModels.JwtResponse;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/autenticacao")
public class AutenticacaoController {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	private UsuariosRepository _usuariosRepository;

	public AutenticacaoController(UsuariosRepository usuariosRepository) {
		_usuariosRepository = usuariosRepository;
	}

	@PostMapping()
	public ResponseEntity postMethodName(@RequestBody JwtRequest entity) { // @RequestHeader() String Authorization) {
		// TODO: process POST request

		if (!entity.cpf.isEmpty() && !entity.password.isEmpty()) {
			Usuario usuario = _usuariosRepository.findFirstByCpf(entity.cpf);

			if (usuario.getSenha().equals(entity.password)) {

				String token = jwtTokenUtil.generateToken(new Usuario(entity.cpf, entity.password));

				return ResponseEntity.ok(new JwtResponse(token));
			}
		}

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}

	@GetMapping()
	public ResponseEntity<String> getMethodName(@RequestParam String token) {
		if (token != null && !token.isEmpty()) {
			try {
				var cpfUser = jwtTokenUtil.getUsernameFromToken(token);

				if (cpfUser != null && !cpfUser.isEmpty()) {

					if (jwtTokenUtil.validateToken(token, _usuariosRepository.findFirstByCpf(cpfUser)))
						return ResponseEntity.ok().build();
				}
			} catch (SignatureException ex) {
				System.out.println("Assinatura de token inválida :" + ex.getMessage());
				return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
			}
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}

}
