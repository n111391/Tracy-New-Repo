Feature: Internet Plus 40Mbps naar 50Mbps

 @test2
  Scenario: internet Plus 40Mbps naar 50Mbps
   Given User launched browser and logged in to Tracy successfully
   When User opens menu item "Aanmaken>Contractkaart>Office plus>mutatie"
   Then User opens tab "Relatie"
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
   And User selects frame "40Mbps/4Mbps" from dropdown "Internet Plus" and clicks on "internet plus details"
   And User completes the steps keuze->Opslaan
   Then Close the application