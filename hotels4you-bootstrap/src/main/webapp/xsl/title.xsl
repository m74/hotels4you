<?xml version="1.0" encoding="UTF-8" ?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	version="1.0">
	<xsl:import href="commons/template.xsl" />

	<xsl:template name="style">
		<link rel="stylesheet" href="{$cp}/css/jquery.typeahead.min.css" />
		<link rel="stylesheet" href="{$cp}/css/title.css" />
	</xsl:template>

	<xsl:template name="scripts">
		<script src="{$cp}/js/jquery.typeahead.min.js" type="text/javascript" />
		<script type="text/javascript" src="{$cp}/js/regionsearch.js" />
	</xsl:template>

	<xsl:template match="body">
		<div class="container main">
			<p>
				<button type="button" class="btn btn-link">
					<span class="glyphicon glyphicon-plus"/>
					<xsl:text> Добавить гостиницу</xsl:text>
				</button>
			</p>
			<p>
				<form id="form-regionsearch" name="form-regionsearch">
					<div class="typeahead-container">
						<div class="typeahead-field">

							<span class="typeahead-query">
								<input id="regionsearch-query" name="regionsearch[query]"
									placeholder="Регион, аэропорт, гостиница ..." autocomplete="off"
									type="search" />
							</span>
							<span class="typeahead-button">
								<button class="btn btn-primary" type="submit">Поиск</button>
							</span>

						</div>
					</div>
				</form>
			</p>
			<p>
				<a href="region/mos/">
					<span class="glyphicon glyphicon-bookmark"/>
					<xsl:text> Москва</xsl:text>
				</a>
				<xsl:text>, </xsl:text>
				<a href="region/spb/">
					<span class="glyphicon glyphicon-bookmark"/>
					<xsl:text> Санкт-Петербург</xsl:text>
				</a>
				<xsl:text>, </xsl:text>
				<a href="all">
					<span class="glyphicon glyphicon-bookmark"/>
					<xsl:text> Другие ...</xsl:text>
				</a>
			</p>

			<p>
				<xsl:value-of select="content" />
			</p>

			<div class="row">
				<div class="col-md-4">
					<a href="#">
						<span class="glyphicon glyphicon-bookmark"/>
						<xsl:text> Конференц залы</xsl:text>
					</a>
				</div>
				<div class="col-md-4">
					<a href="#">
						<span class="glyphicon glyphicon-bookmark"/>
						<xsl:text> Выставки</xsl:text>
					</a>
				</div>
				<div class="col-md-4">
					<a href="#">
						<span class="glyphicon glyphicon-bookmark"/>
						<xsl:text> Молодоженам</xsl:text>
					</a>
				</div>
			</div>
		</div>
	</xsl:template>

</xsl:stylesheet>
