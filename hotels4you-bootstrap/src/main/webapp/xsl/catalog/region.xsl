<?xml version="1.0" encoding="UTF-8" ?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                version="1.0">
    <xsl:import href="../commons/template.xsl"/>

    <xsl:template name="style">
        <link rel="stylesheet" href="{$cp}/css/catalog/region.css"/>
        <link rel="stylesheet" href="{$cp}/css/ion.rangeSlider.css"/>
        <link rel="stylesheet" href="{$cp}/css/ion.rangeSlider.skinHTML5.css"/>
        <link rel="stylesheet" href="{$cp}/css/bootstrap-datepicker.min.css"/>
    </xsl:template>

    <xsl:template name="scripts">
        <script type="text/javascript" src="{$cp}/js/ion.rangeSlider.min.js"/>
        <script type="text/javascript" src="{$cp}/js/bootstrap-datepicker.min.js"/>
        <script type="text/javascript" src="{$cp}/js/bootstrap-datepicker.ru.js"/>
        <script type="text/javascript" src="{$cp}/js/region.js"/>
    </xsl:template>

    <xsl:template match="body">
        <div class="container">
            <xsl:apply-templates select="region"/>
            <div class="col-md-3 sticky" style="padding-top: 10px;">
                <div class="row">
                    <div class="form-group col-md-6">
                        <label for="arrival">Заезд</label>
                        <input class="form-control datepicker" id="arrival" name="arrival"
                               value="{session/arrival}" placeholder="DD.MM.YY"/>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="departure">Выезд</label>
                        <input class="form-control datepicker" id="departure" name="departure"
                               value="{session/departure}" placeholder="DD.MM.YY"/>
                    </div>
                    <div style="padding: 0 15px;">
                        <label for="pricerange">Стоимость</label>
                        <input type="text" id="pricerange" name="pricerange"
                               value="{session/pricerange}"/>
                    </div>
                </div>
            </div>
            <div class="col-md-9" style="padding-top: 10px;">
                <div class="form-group">
                    <label for="filter">Фильтр</label>
                    <input class="form-control" id="filter" name="query"
                           value="{session/query}" autocomplete="off" placeholder="Название гостиницы ..."/>
                </div>
                <div class="form-group">
                    <div class="checkbox">
                        <label>
                            <input type="checkbox"/>
                            Только спец. предложения
                        </label>
                    </div>
                </div>
                <hr/>
                <div id="hotelslist">
                    <xsl:apply-templates select="hotel"/>
                </div>
            </div>
        </div>
    </xsl:template>


    <xsl:template name="content"/>

    <!--<xsl:template match="region">-->
    <!--&lt;!&ndash; <div class="page-header"> <h3> <xsl:value-of select="@title" /> </h3>-->
    <!--</div> &ndash;&gt;-->
    <!--<input name="region" value="{@id}" type="hidden"/>-->
    <!--</xsl:template>-->

    <!--<xsl:template match="hotel-1">-->
    <!--<li>-->
    <!--<a href="{$cp}/hotel/{@id}/">-->
    <!--<xsl:value-of select="@title" />-->
    <!--</a>-->
    <!--</li>-->
    <!--</xsl:template>-->

    <xsl:template match="hotel">
        <div class="row hotel">
            <div class="col col-md-3">
                <img src="{iconUrl}" class="img-responsive img-thumbnail"/>
            </div>
            <div class="col col-md-6">
                <h3>
                    <xsl:value-of select="title"/>
                </h3>
                <p class="description">
                    <small>
                        <xsl:value-of select="annotation"/>
                    </small>
                </p>
            </div>
            <div class="col col-md-3">
                <p class="price">
                    <xsl:value-of select="@price"/>
                </p>
                <p class="buttons">
                    <a href="{$cp}/hotel/{id}/" class="btn btn-default" role="button">
                        <xsl:text>Просмотр</xsl:text>
                    </a>
                </p>
            </div>
        </div>
        <hr/>
    </xsl:template>
</xsl:stylesheet>
