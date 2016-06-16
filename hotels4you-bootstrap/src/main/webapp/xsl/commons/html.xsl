<?xml version="1.0" encoding="UTF-8" ?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	version="1.0">
	<xsl:template match="img">
		<img src="{@src}">
			<xsl:if test="@alt">
				<xsl:attribute name="alt">
          <xsl:value-of select="@alt" />
        </xsl:attribute>
			</xsl:if>
			<xsl:if test="@align">
				<xsl:attribute name="class">
          <xsl:value-of select="@align" />
        </xsl:attribute>
			</xsl:if>
		</img>
	</xsl:template>

	<xsl:template match="a">
		<a href="{@href}">
			<xsl:apply-templates select="@class" />
			<xsl:if test="@title">
				<xsl:attribute name="title">
          <xsl:value-of select="@title" />
        </xsl:attribute>
			</xsl:if>
			<xsl:apply-templates />
		</a>
	</xsl:template>

	<xsl:template match="@class|@id|@style|@colspan|@rowspan|@src|@type|@charset">
		<xsl:attribute name="{name()}">
      <xsl:value-of select="." />
    </xsl:attribute>
	</xsl:template>

	<xsl:template
		match="table|tr|td|th|span|ul|li|ol|h1|h2|h3|h4|h5|b|i|u|strong|p|div|style|hr|br|button">
		<xsl:element name="{name()}">
			<xsl:apply-templates select="@class|@id|@style|@colspan|@rowspan" />
			<xsl:apply-templates />
		</xsl:element>
	</xsl:template>

	<xsl:template match="script">
		<xsl:element name="{name()}">
			<xsl:apply-templates select="@type|@src|@charset" />
			<xsl:apply-templates />
		</xsl:element>
	</xsl:template>

<!-- 	<xsl:template match="style">
		<link rel="stylesheet" type="text/css" href="{@src}" />
	</xsl:template>
	<xsl:template match="br">
		<xsl:element name="{name()}">
			<xsl:apply-templates />
		</xsl:element>
	</xsl:template>
 -->

	<xsl:template match="clear">
		<div class="clear" />
	</xsl:template>

</xsl:stylesheet>
