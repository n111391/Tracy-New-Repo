Feature: Glasvezel Aansluiting 1 Gbps onnet Korting eenmalig 24 maanden 1 N 50

 @test22
  Scenario: Glasvezel Aansluiting 1 Gbps onnet Korting eenmalig 24 maanden 1 N 50%
   Given User launched browser and logged in to Tracy successfully
   Then User opens menu item "Aanmaken>Contractkaart>Office plus>nieuw"
   And User opens tab "Relatie"
   And User clicks on "Relatienummer Icon"
   And User performs search using "Relatienummer"
   Then User selects "B2B D2D" from dropdown "Scorende AM"
   And User clicks on "Debiteurnummer Icon"
   Then User opens tab "Aanvraag"
   And User enters "caseomschrijving" into "caseomschrijving" field
   And User enters "contractsduur" into "contractsduur" field
   And User enters future date into "gewensteleverdatum" field
   Then User opens tab "contractkart"
   And User clicks on frame element "Toevoegen"
   And User clicks on frame element "relatieadres"
   And User selects frame "Zakelijk Internet Complete Pro 300/40" from dropdown "zakelijk internet pro" and clicks on "zakelijkinternetpro details"
   And User completes the steps keuze->Opslaan
   Then Close the application
