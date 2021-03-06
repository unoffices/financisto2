/*
 * Copyright (c) 2011 Denis Solonenko.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 */

package ru.orangesoftware.financisto2.test.export;

import ru.orangesoftware.financisto2.test.db.AbstractDbTest;
import ru.orangesoftware.financisto2.model.Account;
import ru.orangesoftware.financisto2.model.AccountType;
import ru.orangesoftware.financisto2.model.Currency;
import ru.orangesoftware.financisto2.test.builders.CurrencyBuilder;

/**
 * Created by IntelliJ IDEA.
 * User: Denis Solonenko
 * Date: 2/4/11 10:23 PM
 */
public abstract class AbstractImportExportTest extends AbstractDbTest {

    protected Account createFirstAccount() {
        Currency c = createCurrency("SGD");
        Account a = new Account();
        a.title = "My Cash Account";
        a.type = AccountType.CASH.name();
        a.currency = c;
        a.totalAmount = 10000;
        a.note = "AAA\nBBB:CCC";
        db.saveAccount(a);
        assertNotNull(db.load(Account.class, a.id));
        return a;
    }

    protected Account createSecondAccount() {
        Currency c = createCurrency("CZK");
        Account a = new Account();
        a.title = "My Bank Account";
        a.type = AccountType.BANK.name();
        a.currency = c;
        a.totalAmount = 23450;
        db.saveAccount(a);
        assertNotNull(db.load(Account.class, a.id));
        return a;
    }

    private Currency createCurrency(String currency) {
        Currency c = CurrencyBuilder.withDb(db)
                .title("Singapore Dollar")
                .name(currency)
                .separators("''", "'.'")
                .symbol("S$")
                .create();
        assertNotNull(db.load(Currency.class, c.id));
        return c;
    }

}
