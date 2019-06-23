/*
 * ATLauncher - https://github.com/ATLauncher/ATLauncher
 * Copyright (C) 2013-2019 ATLauncher
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.atlauncher.gui.card;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.atlauncher.data.Instance;
import com.atlauncher.data.Language;
import com.atlauncher.data.curse.CurseMod;
import com.atlauncher.gui.dialogs.CurseModFileSelectorDialog;
import com.atlauncher.utils.OS;

@SuppressWarnings("serial")
public final class CurseModCard extends JPanel {
    public final CurseMod mod;

    public CurseModCard(final CurseMod mod, final Instance instance) {
        this.mod = mod;
        setLayout(new BorderLayout());

        JPanel summaryPanel = new JPanel(new BorderLayout());
        JTextArea summary = new JTextArea();
        summary.setText(mod.summary);
        summary.setBorder(BorderFactory.createEmptyBorder());
        summary.setEditable(false);
        summary.setHighlighter(null);
        summary.setLineWrap(true);
        summary.setWrapStyleWord(true);
        summary.setEditable(false);
        summaryPanel.add(summary, BorderLayout.CENTER);
        summaryPanel.setBorder(new EmptyBorder(0, 0, 10, 0));

        JPanel buttonsPanel = new JPanel(new FlowLayout());
        JButton addButton = new JButton(Language.INSTANCE.localize("common.add"));
        JButton viewButton = new JButton(Language.INSTANCE.localize("common.view"));
        buttonsPanel.add(addButton);
        buttonsPanel.add(viewButton);

        addButton.addActionListener(e -> {
            new CurseModFileSelectorDialog(mod, instance);
        });

        viewButton.addActionListener(e -> {
            OS.openWebBrowser(mod.websiteUrl);
        });

        add(summaryPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);

        TitledBorder border = new TitledBorder(null, mod.name, TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.ABOVE_TOP, new Font("SansSerif", Font.BOLD, 12));
        setBorder(border);
    }
}
