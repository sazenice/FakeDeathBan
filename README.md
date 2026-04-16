# Fake Death Ban
## Plugin od sazenice, pro corrupted universe ❤️
___ 

### Úvod
Death ban pluginy jsou určeny pro SMP servery.

#### Co ale FakeDeathBan je?
Fake Death Ban (od teď jako **FDB**) je něco jako obvyklý deathban plugin,  
jen je určen pro skriptované servery (*jako třeba __Corrupted Universe__ :)*)

___

### Jak to funguje?
FDB nezabanuje hráče,
jen je dá do spectator módu a zakáže jakýkoliv pohyb

___

### Příkazy
FDB má 7 příkazů

<details>
<summary><strong>UnDeathBan</strong></summary>

Teleportuje všechny hráče s death banem k tobě a odstraní jim deathban.

**Povolení:** OP

**Použití:** `/undeathban [hráč...]`

**Aliasy:**
- udb
- undb
- udban
- udeathb
- undeathb
- undban
- udeathban

</details>

<details>
<summary><strong>DefaultGamemode</strong></summary>

Nastaví výchozí gamemode pro hráče, kterým se odstraní deathban.

**Povolení:** OP

**Použití:** `/defg <gamemode>`

**Aliasy:**
- defaultgamemode
- dgamemode
- defaultg
- dg

</details>

<details>
<summary><strong>Version</strong></summary>

Ukáže informace o pluginu.

**Povolení:** Všichni

**Použití:** `/version`

</details>

<details>
<summary><strong>Freeze</strong></summary>

Zmrazí všechny hráče, co mají deathban.

**Povolení:** OP

**Použití:** `/freeze [hráč...]`

**Aliasy:**
- fr

</details>

<details>
<summary><strong>UnFreeze</strong></summary>

Rozmrazí všechny hráče, co jsou zmraženi.

**Povolení:** OP

**Použití:** `/unfreeze [hráč...]`

**Aliasy:**
- unfr

</details>

<details>
<summary><strong>SetSpectate</strong></summary>

Nastaví hráče, který se bude spectatovat, když někdo má deathban.

**Povolení:** OP

**Použití:** `/setspectate <hráč>`

**Aliasy:**
- sets
- setspec
- sspec
- ss

</details>

<details>
<summary><strong>Spectate</strong></summary>

Nastaví hráče, který se bude spectatovat, když ty máš deathban.

**Povolení:** Všichni

**Použití:** `/spectate <hráč>`

**Aliasy:**
- spec
- s

</details>

___

### Posluchače
FDB má 4 posluchače:

- **PlayerJoin**  
  Vypne připojovací zprávu

- **PlayerQuit**  
  Vypne odpojovací zprávu

- **PlayerDeath**  
  Dá deathban hráči, co umřel

- **PlayerMove**  
  Zablokuje pohyb, pokud má hráč deathban  