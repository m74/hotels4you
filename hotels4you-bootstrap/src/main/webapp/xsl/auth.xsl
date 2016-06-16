<?xml version="1.0" encoding="UTF-8" ?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	version="1.0">
	<xsl:import href="commons/template.xsl" />

	<xsl:template name="style">
		<link rel="stylesheet" href="{$cp}/css/grid-divider.css" />
	</xsl:template>

	<xsl:template name="scripts">
		<script type="text/javascript" src="{$cp}/js/loginza.js" />
	</xsl:template>

	<xsl:template name="content">

		<div class="container">
			<xsl:choose>
				<xsl:when test="session/user.id">
					<center>Вы уже авторизованы.</center>
				</xsl:when>
				<xsl:otherwise>
					<div class="lead" style="text-align: center">Для продолжения, Вы должны пройти
						авторизацию.</div>
					<div class="row grid-divider">
						<div class="col-sm-6">
							<div>
								<div class="page-header">
									<h3>Вход с помощью социальных сетей</h3>
								</div>
								<div id="loginza"></div>
							</div>
						</div>
						<div class="col-sm-6">
							<div style="margin: 0 20px">
								<form action="{$cp}/login">
									<div class="page-header">
										<h3>Вход по email</h3>
									</div>
									<xsl:apply-templates select="session/message" />
									<div class="form-group">
										<input class="form-control" id="login" name="id"
											placeholder="Email" />
									</div>
									<div class="form-group">
										<input type="password" class="form-control" id="password"
											name="password" placeholder="Password" />
									</div>
									<button type="submit" class="btn btn-default">Войти</button>
								</form>
								<hr />
								<ul>
									<li>
										<a href="register/">Регистрация</a>
									</li>
									<li>
										<a href="restore/">Восстановление пароля</a>
									</li>
								</ul>
							</div>
						</div>
					</div>
				</xsl:otherwise>
			</xsl:choose>
		</div>
	</xsl:template>

</xsl:stylesheet>
