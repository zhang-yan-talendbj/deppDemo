<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fn="http://www.w3.org/2005/xpath-functions">
<xsl:template name="NoBlanks" match="@*|node()">
		<xsl:if test="(self::text() and string-length(self::text()[normalize-space(.)]) > 0) or count(.//text()[normalize-space(.)]) > 0 or count(../attribute::node()) > 0">
			<xsl:copy>
				<xsl:apply-templates select="@*|node()"/>xsl work success
			</xsl:copy>
		</xsl:if>
	</xsl:template>
</xsl:stylesheet>
