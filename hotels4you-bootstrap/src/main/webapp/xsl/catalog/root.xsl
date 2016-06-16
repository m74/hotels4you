<?xml version="1.0" encoding="UTF-8" ?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	version="1.0">
	<xsl:import href="../commons/template.xsl" />

	<xsl:template match="region">
		<div class="col-xs-6 col-sm-4 col-md-3 col-lg-2">
			<a href="region/{@id}/">
				<xsl:value-of select="@title" />
			</a>
		</div>
	</xsl:template>

	<xsl:template name="content">
		<div class="container">
			<div class="row">
				<xsl:apply-templates select="region" />
			</div>
		</div>
	</xsl:template>

</xsl:stylesheet>
