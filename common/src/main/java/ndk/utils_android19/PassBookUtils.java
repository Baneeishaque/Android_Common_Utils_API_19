package ndk.utils_android19;

import android.content.Context;
import android.util.Log;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import ndk.utils_android16.Date_Utils;
import ndk.utils_android16.Toast_Utils;
import ndk.utils_android16.models.sortable_tableView.pass_book.Pass_Book_Entry;
import ndk.utils_android16.models.sortable_tableView.pass_book.Pass_Book_Entry_v2;

import static ndk.utils_android16.Pdf_Utils.addEmptyLine;

public class PassBookUtils extends ndk.utils_android16.Pass_Book_Utils {

    public static boolean createPassBookPdf(Context context, File passBookPdf, String applicationName, String additionalTitles) {

        if (FolderUtils.createDocumentsApplicationFolder(context, applicationName)) {
            try {
                OutputStream output = new FileOutputStream(passBookPdf);

                //Step 1
                Document document = new Document(PageSize.A4);

                //Step 2
                PdfWriter.getInstance(document, output);

                //Step 3
                document.open();

                //Step 4 Add content

                Paragraph title = new Paragraph(applicationName + ", Pass Book" + additionalTitles, FontFactory.getFont(FontFactory.TIMES_ROMAN, 16, Font.BOLD, BaseColor.BLACK));

                addEmptyLine(title, 1);
                title.setAlignment(Element.ALIGN_CENTER);
                document.add(title);

                PdfPTable table = new PdfPTable(5);
                if (v2_flag) {
                    table = new PdfPTable(6);
                }

                PdfPCell c1 = new PdfPCell(new Phrase("Date"));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);

                PdfPCell c2 = new PdfPCell(new Phrase("Particulars"));
                c2.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c2);

                if (v2_flag) {
                    PdfPCell c3 = new PdfPCell(new Phrase("To A/C"));
                    c3.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(c3);

                    PdfPCell c4 = new PdfPCell(new Phrase("Credit"));
                    c4.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(c4);

                    PdfPCell c5 = new PdfPCell(new Phrase("Debit"));
                    c5.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(c5);

                } else {

                    PdfPCell c3 = new PdfPCell(new Phrase("Debit"));
                    c3.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(c3);

                    PdfPCell c4 = new PdfPCell(new Phrase("Credit"));
                    c4.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(c4);

                }

                PdfPCell c5 = new PdfPCell(new Phrase("Balance"));
                c5.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c5);

                if (v2_flag) {
                    if (!current_pass_book_entries_v2.isEmpty()) {
                        for (Pass_Book_Entry_v2 pass_book_entry_v2 : current_pass_book_entries_v2) {
                            table.addCell(Date_Utils.normal_date_time_short_year_format.format(pass_book_entry_v2.getInsertion_date()));
                            table.addCell(pass_book_entry_v2.getParticulars());
                            table.addCell(String.valueOf(pass_book_entry_v2.getSecond_account_name()));
                            table.addCell(String.valueOf(pass_book_entry_v2.getCredit_amount()));
                            table.addCell(String.valueOf(pass_book_entry_v2.getDebit_amount()));
                            table.addCell(String.valueOf(pass_book_entry_v2.getBalance()));
                        }
                    }
                } else {
                    if (!current_pass_book_entries.isEmpty()) {
                        for (Pass_Book_Entry pass_book_entry : current_pass_book_entries) {
                            table.addCell(Date_Utils.normal_date_time_short_year_format.format(pass_book_entry.getInsertion_date()));
                            table.addCell(pass_book_entry.getParticulars());
                            table.addCell(String.valueOf(pass_book_entry.getDebit_amount()));
                            table.addCell(String.valueOf(pass_book_entry.getCredit_amount()));
                            table.addCell(String.valueOf(pass_book_entry.getBalance()));
                        }
                    }

                }

                document.add(table);

                //Step 5: Close the document
                document.close();
                return true;

            } catch (DocumentException | FileNotFoundException e) {
                e.printStackTrace();
                Log.i(applicationName, "Pdf Creation failure " + e.getLocalizedMessage());
                Toast_Utils.longToast(context, "Pdf fail");
            }
        }
        return false;
    }
}
